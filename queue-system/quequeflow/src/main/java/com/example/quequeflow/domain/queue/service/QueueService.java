package com.example.quequeflow.domain.queue.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.quequeflow.global.common.constants.BaseResponseStatus;
import com.example.quequeflow.global.exception.InvalidCustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueueService {
	private final RedisTemplate<String, String> redisTemplate;
	// 대기 큐
	private final String USER_QUEUE_WAIT_KEY = "queue:wait";
	// 진행 큐
	private final String USER_QUEUE_PROCEED_KEY = "queue:proceed";
	private final int MAX_PROCEED_SIZE = 1;

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

	//List<> list ; // 식별자 : 만료시간

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
		String waitQueueKey = getWaitQueueKey(boardIdx);

		Long removedCount = redisTemplate.opsForZSet().remove(waitQueueKey, userIdx.toString());

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
}
