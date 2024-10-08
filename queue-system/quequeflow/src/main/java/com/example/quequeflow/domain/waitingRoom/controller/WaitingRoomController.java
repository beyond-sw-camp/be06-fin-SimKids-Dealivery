package com.example.quequeflow.domain.waitingRoom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quequeflow.domain.waitingRoom.model.dto.WaitingRoomDto;
import com.example.quequeflow.domain.waitingRoom.service.WaitingRoomService;
import com.example.quequeflow.global.common.constants.BaseResponse;
import com.example.quequeflow.global.common.constants.BaseResponseStatus;
import com.example.quequeflow.global.token.QueueTokenUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WaitingRoomController {
	private final WaitingRoomService waitingRoomService;
	private final QueueTokenUtil queueTokenUtil;

	// 1. 최초 대기열 진입점
	@GetMapping("/waiting-room")
	public BaseResponse enter(
		@RequestParam(name = "boardIdx") Long boardIdx,
		@RequestParam(name = "userIdx") Long userIdx, HttpServletRequest request) {

		String key = "user-queue-%d-token".formatted(boardIdx);
		Cookie[] cookies = request.getCookies();
		String token = "";

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}

		// 쿠키값이 있다 = 내가 들어갈 수 있는 순위다
		boolean isAllowed = queueTokenUtil.isAllowedByToken(boardIdx, userIdx, token);

		if (isAllowed) {
			return new BaseResponse(BaseResponseStatus.SUCCESS);
		}

		WaitingRoomDto.WaitingRoomResponse response = waitingRoomService.enter(boardIdx, userIdx);

		// 대기열에 등록 후, 현재 순위를 반환
		return new BaseResponse(response);

	}
}
