package com.example.quequeflow.domain.queue.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.quequeflow.global.common.constants.BaseResponseStatus;
import com.example.quequeflow.global.exception.InvalidCustomException;

import lombok.RequiredArgsConstructor;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueService {
	private final RedisTemplate<String, String> redisTemplate;
	// 대기 큐
	private final String USER_QUEUE_WAIT_KEY = "queue:wait";
	// 진행 큐
	private final String USER_QUEUE_PROCEED_KEY = "queue:proceed";
	@Value("${queue.proceed.max-size}")
	private int MAX_PROCEED_SIZE;

	@Value("${queue.allow.user.cnt}")
	private Long MAX_ALLOW_USER_CNT;

	private final String DUMMY_KEY = "dummy";

	public Boolean createQueue(Long boardIdx, LocalDateTime endedAt) {
		String waitQueueKey = USER_QUEUE_WAIT_KEY + ":" + boardIdx;
		String processedQueueKey = USER_QUEUE_PROCEED_KEY + ":" + boardIdx;
		LocalDateTime endedAtPlusMinutes = endedAt.plusMinutes(10);
		long unixTimestamp = endedAtPlusMinutes.toInstant(ZoneOffset.UTC).getEpochSecond();

		// 더미 데이터를 ZSet에 추가
		boolean addedToWaitQueue = Boolean.TRUE.equals(redisTemplate.opsForZSet().add(waitQueueKey, "dummy", unixTimestamp));
		boolean addedToProcessedQueue = Boolean.TRUE.equals(redisTemplate.opsForZSet().add(processedQueueKey, "dummy", unixTimestamp));

		// endedAt에 1분 추가
		LocalDateTime expirationTime = endedAt.plusMinutes(1);
		long expirationTimeInSeconds = Duration.between(LocalDateTime.now(), expirationTime).getSeconds();

		// 만료 시간 설정
		if (addedToWaitQueue) {
			redisTemplate.expire(waitQueueKey, expirationTimeInSeconds, TimeUnit.SECONDS);
		}
		if (addedToProcessedQueue) {
			redisTemplate.expire(processedQueueKey, expirationTimeInSeconds, TimeUnit.SECONDS);
		}

		return addedToWaitQueue && addedToProcessedQueue;
	}


	// **쿠키값 없고 대기열 등록 안돼있을 때** 대기열 등록하는 메소드
	public Long registerWaitQueue(final Long boardIdx, final Long userIdx) {
		String queueKey = choiceQueue(boardIdx);
		long unixTimestamp = Instant.now().getEpochSecond();

		// 1. 대기 없이 바로 진입
		if (queueKey.equals(getProceedQueueKey(boardIdx))) {
			redisTemplate.opsForZSet().add(queueKey, userIdx.toString(), unixTimestamp);
			return -1L;
		}

		// 2. 대기 존재
		// key: 대기열 키, value: {유저 id, 유닉스 타임 스탬프} 기준으로 Redis에 등록
		Boolean added = redisTemplate.opsForZSet().add(queueKey, userIdx.toString(), unixTimestamp);

		// 이미 등록되어 있으면 예외 발생 시킴 -> registerWaitQueue 호출한 try catch의 catch로 이동
		if (Boolean.FALSE.equals(added)) {
			throw new InvalidCustomException(BaseResponseStatus.QUEUE_ALREADY_REGISTERED_USER);
		}

		// 현재 순위 반환
		Long rank = redisTemplate.opsForZSet().rank(queueKey, userIdx.toString());
		return (rank != null && rank >= 0) ? rank + 1 : -1;
	}

	public boolean removeUserFromQueue(Long boardIdx, Long userIdx) {
		Long removedCount = 0L;

		if (isUserInProcceedQueue(boardIdx, userIdx)) { // 사용자가 진행 큐에 있을 때
			String proceedQueueKey = getProceedQueueKey(boardIdx);
			removedCount = redisTemplate.opsForZSet().remove(proceedQueueKey, userIdx.toString());
		}
		else { // 사용자가 대기 큐에 있을 때
			String waitQueueKey = getWaitQueueKey(boardIdx);
			removedCount = redisTemplate.opsForZSet().remove(waitQueueKey, userIdx.toString());
		}

		System.out.println("지워진 갯수 :: " + removedCount);


		return (removedCount != null && removedCount > 0);
	}


	private String choiceQueue(Long boardIdx) {
		String waitQueueKey = getWaitQueueKey(boardIdx);
		String proceedQueueKey = getProceedQueueKey(boardIdx);
		Long waitQueueCount = getCount(waitQueueKey);
		Long proceedQueueCount = getCount(proceedQueueKey);

		if (waitQueueCount == 0 && proceedQueueCount < MAX_PROCEED_SIZE) {
			return proceedQueueKey;
		}
		return waitQueueKey;
	}

	private Long getCount(String waitQueueKey) {
		Long queueCount = redisTemplate.opsForZSet().zCard(waitQueueKey);
		if (queueCount == null) {
			return 0L;
		}
		return queueCount;
	}

	// 현재 순위 반환
	public Long getRank(final Long boardIdx, final Long userIdx) {
		String waitQueueKey = getWaitQueueKey(boardIdx);
		Long rank = redisTemplate.opsForZSet().rank(waitQueueKey, userIdx.toString());
		return (rank != null && rank >= 0) ? rank + 1 : -1;
	}

	private String getWaitQueueKey(final Long boardIdx) {
		return USER_QUEUE_WAIT_KEY + ":" + boardIdx;
	}

	private String getProceedQueueKey(final Long boardIdx) {
		return USER_QUEUE_PROCEED_KEY + ":" + boardIdx;
	}

	private Long extractBoardIdxFromKey(String key) {
		String[] parts = key.split(":");
		if (parts.length == 3) {
			try {
				return Long.parseLong(parts[2]);
			} catch (NumberFormatException e) {
				log.error("Invalid boardIdx format in key: {}", key, e);
			}
		}
		return null;
	}

	public Set<String> getProceedQueueKeys() {
		Set<String> keys = new HashSet<>();

		ScanOptions scanOptions = ScanOptions.scanOptions().match("queue:proceed*").count(1000).build();
		Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection().scan(scanOptions);

		while (cursor.hasNext()) {
			keys.add(new String(cursor.next()));
		}

		cursor.close();

		return keys;
	}

	public Long allowUser(final Long boardIdx, final Long count) { // 상위 (0 부터 count-1) count명의 유저를 뽑음

		String waiteQueueKey = getWaitQueueKey(boardIdx);
		String proceedQueueKey = getProceedQueueKey(boardIdx);


		Set<String> members = redisTemplate.opsForZSet().range(waiteQueueKey, 0, count - 1);


		if (members == null || members.isEmpty()) {
			return 0L;
		}

		for (String member : members) { // 대기 큐에서 삭제하고, proceed로 이동ㅅ킴

			if (DUMMY_KEY.equals(member)) {
				continue;
			}

			redisTemplate.opsForZSet().remove(waiteQueueKey, member);
			redisTemplate.opsForZSet().add(proceedQueueKey, member, Instant.now().getEpochSecond());
		}

		return (long) members.size();
	}

	public boolean isUserInProcceedQueue(Long boardIdx, Long userIdx) {
		String proceedQueueKey = getProceedQueueKey(boardIdx);

		Long rank = redisTemplate.opsForZSet().rank(proceedQueueKey, userIdx.toString());

		return rank != null;
	}


	@Scheduled(fixedRateString = "${queue.delay}")
	public void scheduleAllowUser() {

		log.info("called scheduling...");
		Set<String> proccedQueueKeys = getProceedQueueKeys();

		for (String key : proccedQueueKeys) {
			if (DUMMY_KEY.equals(key)) {
				continue;
			}

			Long cnt = getCount(key);
			if(cnt < MAX_ALLOW_USER_CNT) {
				Long boardIdx = extractBoardIdxFromKey(key);
				this.allowUser(boardIdx, MAX_ALLOW_USER_CNT - cnt); // 여유분 만큼 진행큐로 이동
			}
		}
	}
}
