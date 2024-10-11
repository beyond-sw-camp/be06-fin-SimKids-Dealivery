package com.example.quequeflow.domain.queue.service;

import java.time.Duration;
import java.time.Instant;

import java.util.Set;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.quequeflow.global.common.constants.BaseResponseStatus;
import com.example.quequeflow.global.exception.InvalidCustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueueService {
	private final RedisTemplate<String, String> redisTemplate;
	private final String USER_QUEUE_WAIT_KEY = "queue:wait";
	private final String USER_QUEUE_PROCEED_KEY = "queue:proceed";

	//List<> list ; // 식별자 : 만료시간

	// **쿠키값 없고 대기열 등록 안돼있을 때** 대기열 등록하는 메소드
	public Long registerWaitQueue(final Long boardIdx, final Long userIdx) {
		String waitQueueKey = getWaitQueueKey(boardIdx);
		long unixTimestamp = Instant.now().getEpochSecond();
		// key: 대기열 키, value: {유저 id, 유닉스 타임 스탬프} 기준으로 Redis에 등록
		Boolean added = redisTemplate.opsForZSet().add(waitQueueKey, userIdx.toString(), unixTimestamp);

		// 이미 등록되어 있으면 예외 발생 시킴 -> registerWaitQueue 호출한 try catch의 catch로 이동
		if (Boolean.FALSE.equals(added)) {
			throw new InvalidCustomException(BaseResponseStatus.QUEUE_ALREADY_REGISTERED_USER);
		}

		// 현재 순위 반환
		Long rank = redisTemplate.opsForZSet().rank(waitQueueKey, userIdx.toString());
		return (rank != null && rank >= 0) ? rank + 1 : -1;
	}

	public boolean removeUserFromQueue(Long boardIdx, Long userIdx) {
		String waitQueueKey = getWaitQueueKey(boardIdx);

		Long removedCount = redisTemplate.opsForZSet().remove(waitQueueKey, userIdx.toString());

		return (removedCount != null && removedCount > 0);
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
