package com.example.quequeflow.domain.waitingRoom.service;

import org.springframework.stereotype.Service;

import com.example.quequeflow.domain.queue.service.QueueService;
import com.example.quequeflow.domain.waitingRoom.model.dto.WaitingRoomDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WaitingRoomService {
	private final QueueService queueService;

	public WaitingRoomDto.WaitingRoomResponse enter(Long boardIdx, Long userIdx) {
		Long rank = null;
		// 쿠키 없으면
		try {
			// 대기열에 등록(redis에 등록)하고 현재 순위 반환
			rank = queueService.registerWaitQueue(boardIdx, userIdx);
		} catch (Exception e) {
			// 현재 순위 반환
			rank = queueService.getRank(boardIdx, userIdx);
		}
		return WaitingRoomDto.WaitingRoomResponse.builder().rank(rank).build();
	}

	public boolean isUserInProceedQueue(Long boardIdx, Long userIdx) {
		return queueService.isUserInProcceedQueue(boardIdx, userIdx);
	}
}
