package com.example.quequeflow.domain.queue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quequeflow.global.common.constants.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QueueController {

	// TODO
	// 1. getRankUser 메소드 완성
	// 2. 일정 시간마다 대기열에 유저 빼내기 + proceed Queue에 넣기

	/*
	front-end의 while에서 계속 호출되는 함수
	 * */
	@GetMapping("/rank")
	public BaseResponse getRankUser(@RequestParam(name = "queue", defaultValue = "default") String queue,
		@RequestParam(name = "user_id") Long userId) {
		/*
		if (순위 확인해서 현재 순위 <= 순위 임계값) : 쿠키 세팅해서 응답
		else : 쿠키 세팅 X 못들어간다 응답
		* */
		return null;
	}
}
