package com.example.quequeflow.domain.queue.controller;

import com.example.quequeflow.domain.queue.dto.QueueDto;
import com.example.quequeflow.domain.queue.dto.QueueDto.QueueRankResponse;
import com.example.quequeflow.domain.queue.service.QueueService;
import com.example.quequeflow.global.common.constants.BaseResponseStatus;
import com.example.quequeflow.global.token.QueueTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quequeflow.global.common.constants.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QueueController {

	private final QueueService queueService;
	private final QueueTokenUtil queueTokenUtil;

	// TODO
	// 1. getRankUser 메소드 완성
	// 2. 일정 시간마다 대기열에 유저 빼내기 + proceed Queue에 넣기

	/*
	front-end의 while에서 계속 호출되는 함수
	 * */
	@GetMapping("/rank")
	public BaseResponse getRankUser(@RequestParam(name = "boardIdx") Long boardIdx,
									@RequestParam(name = "userIdx") Long userIdx, HttpServletResponse response) {
		/*
		if (순위 확인해서 현재 순위 <= 순위 임계값) : 쿠키 세팅해서 응답
		else : 쿠키 세팅 X 못들어간다 응답
		* */

		Long rank = queueService.getRank(boardIdx, userIdx);
		System.out.println("Rank!!!!!!! " + rank);
		if (rank <= 1) {
			String key = "user-queue-%d-token".formatted(boardIdx);
			String token = queueTokenUtil.generateToken(boardIdx, userIdx);

			ResponseCookie cookie = ResponseCookie.from(key, token)
					.maxAge(Duration.ofSeconds(300))
					.path("/")
					.build();
			response.addHeader("Set-Cookie", cookie.toString());
			return new BaseResponse(BaseResponseStatus.SUCCESS);
		}

		QueueDto.QueueRankResponse res = QueueRankResponse.builder().rank(rank).build();

		return new BaseResponse(BaseResponseStatus.WAITING_IN_QUEUE, res);
	}

	@PostMapping("/delete")
	public BaseResponse deleteQueueToken(@RequestBody QueueDto.QueueDeleteRequest req) {

		boolean isDeleted = queueService.removeUserFromQueue(req.getBoardIdx(), req.getUserIdx());

		if (isDeleted) {
			return new BaseResponse(BaseResponseStatus.SUCCESS);
		} else {
			return new BaseResponse(BaseResponseStatus.FAIL);
		}
	}

}
