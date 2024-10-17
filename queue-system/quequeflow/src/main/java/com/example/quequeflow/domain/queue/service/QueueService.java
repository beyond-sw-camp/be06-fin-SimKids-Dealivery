package com.example.quequeflow.domain.queue.service;

import io.lettuce.core.RedisException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueService {
	private final RedissonClient redissonClient;
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

		// endedAt에서 미세한 시간 차이를 추가한 점수 부여 (나노초 단위로 계산)
		Instant inst = endedAt.plusMinutes(10).toInstant(ZoneOffset.UTC);
		long time = inst.getEpochSecond();
		time *= 1000000000L;  // 초 단위를 나노초 단위로 변환
		time += inst.getNano();  // 나노초를 더해 미세한 차이 부여

		// Redisson의 ScoredSortedSet을 사용하여 대기열 생성, StringCodec을 사용
		RScoredSortedSet<String> waitQueue = redissonClient.getScoredSortedSet(waitQueueKey);
		RScoredSortedSet<String> processedQueue = redissonClient.getScoredSortedSet(processedQueueKey);

		// 더미 데이터를 ZSet에 추가
		boolean addedToWaitQueue = waitQueue.add(time, DUMMY_KEY);
		boolean addedToProcessedQueue = processedQueue.add(time, DUMMY_KEY);

		// endedAt을 기준으로 만료 시간 설정 (ZSet 만료 시간 = endedAt에서 현재 시간까지의 시간)
		long ttl = endedAt.toInstant(ZoneOffset.UTC).getEpochSecond() - Instant.now().getEpochSecond();
		if (ttl > 0) {
			waitQueue.expire(ttl, TimeUnit.SECONDS);
			processedQueue.expire(ttl, TimeUnit.SECONDS);
		}

		return addedToWaitQueue && addedToProcessedQueue;
	}


	// **쿠키값 없고 대기열 등록 안돼있을 때** 대기열 등록하는 메소드
	public Long registerWaitQueue(final Long boardIdx, final Long userIdx) {
		String queueKey = choiceQueue(boardIdx);

		// 미세한 시간 차이를 추가한 점수 부여 (현재 타임스탬프에 사용자 ID를 더하는 방식)
		Instant inst = Instant.now();
		long time = inst.getEpochSecond();
		time *= 1000000000l;
		time += inst.getNano();

		RScoredSortedSet<String> queue = redissonClient.getScoredSortedSet(queueKey);

		// 대기 없이 바로 진입
		if (queueKey.equals(getProceedQueueKey(boardIdx))) {
			queue.add(time, userIdx.toString());
			return -1L;
		}

		// 대기 존재 시 등록
		boolean added = queue.add(time, userIdx.toString());

		if (!added) {
			throw new RuntimeException("User already registered in queue");
		}

		// 현재 순위 반환
		Long rank = Long.valueOf(queue.rank(userIdx.toString()));
		return (rank != null && rank >= 0) ? rank + 1 : -1;
	}

	public boolean removeUserFromQueue(Long boardIdx, Long userIdx) {
		RScoredSortedSet<String> waitQueue = redissonClient.getScoredSortedSet(getWaitQueueKey(boardIdx));
		RScoredSortedSet<String> proceedQueue = redissonClient.getScoredSortedSet(getProceedQueueKey(boardIdx));

		boolean removedCount;
		if (isUserInProcceedQueue(boardIdx, userIdx)) {
			removedCount = proceedQueue.remove(userIdx.toString());
		} else {
			removedCount = waitQueue.remove(userIdx.toString());
		}

		return removedCount;
	}

	private String choiceQueue(Long boardIdx) {
		String waitQueueKey = getWaitQueueKey(boardIdx);
		String proceedQueueKey = getProceedQueueKey(boardIdx);

		RScoredSortedSet<String> waitQueue = redissonClient.getScoredSortedSet(waitQueueKey);
		RScoredSortedSet<String> proceedQueue = redissonClient.getScoredSortedSet(proceedQueueKey);

		if (waitQueue.size() == 1 && proceedQueue.size() < MAX_PROCEED_SIZE) {
			return proceedQueueKey;
		}
		return waitQueueKey;
	}

	private Long getCount(String queueKey) {
		RScoredSortedSet<String> queue = redissonClient.getScoredSortedSet(queueKey);
		return (long) queue.size();  // ZSet에서 요소의 개수를 반환
	}


	// 현재 순위 반환
	public Long getRank(final Long boardIdx, final Long userIdx) {
		RScoredSortedSet<String> waitQueue = redissonClient.getScoredSortedSet(getWaitQueueKey(boardIdx));
		Long rank = Long.valueOf(waitQueue.rank(userIdx.toString()));
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
		try {
			Iterable<String> resultKeys = redissonClient.getKeys().getKeysByPattern("queue:proceed*");
			resultKeys.forEach(keys::add);
		} catch (RedisException e) {
			log.error("Error retrieving keys from Redis: ", e);
		}
		return keys;
	}

	public Long allowUser(final Long boardIdx, final Integer count) {
		String waitQueueKey = getWaitQueueKey(boardIdx);
		String proceedQueueKey = getProceedQueueKey(boardIdx);

		RScoredSortedSet<String> waitQueue = redissonClient.getScoredSortedSet(waitQueueKey);
		RScoredSortedSet<String> proceedQueue = redissonClient.getScoredSortedSet(proceedQueueKey);

		// 상위 count명의 유저를 추출
		Collection<String> members = waitQueue.valueRange(0, count - 1);

		if (members == null || members.isEmpty()) {
			return 0L;
		}

		for (String member : members) {
			if (DUMMY_KEY.equals(member)) {
				continue;
			}

			waitQueue.remove(member);  // 대기 큐에서 삭제
			proceedQueue.add(Instant.now().getEpochSecond(), member);  // 진행 큐로 이동
		}

		return (long) members.size();
	}

	public boolean isUserInProcceedQueue(Long boardIdx, Long userIdx) {
		RScoredSortedSet<String> proceedQueue = redissonClient.getScoredSortedSet(getProceedQueueKey(boardIdx));
		return proceedQueue.contains(userIdx.toString());
	}

	@Scheduled(fixedRateString = "${queue.delay}")
	public void scheduleAllowUser() {

		log.info("called scheduling...");
		Set<String> proccedQueueKeys = getProceedQueueKeys();

		for (String key : proccedQueueKeys) {
			Long cnt = getCount(key);
			if(cnt < MAX_ALLOW_USER_CNT) {
				Long boardIdx = extractBoardIdxFromKey(key);
				this.allowUser(boardIdx, (int) (MAX_ALLOW_USER_CNT - cnt)); // 여유분 만큼 진행큐로 이동
			}
		}
	}

}
