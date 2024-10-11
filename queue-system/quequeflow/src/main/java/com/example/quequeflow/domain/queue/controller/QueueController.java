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

	@GetMapping("/rank")
	public BaseResponse getRankUser(@RequestParam(name = "boardIdx") Long boardIdx,
									@RequestParam(name = "userIdx") Long userIdx, HttpServletResponse response) {

		boolean isAllowed = queueService.isUserInProcceedQueue(boardIdx, userIdx);

		if (isAllowed) {
			String key = "user-queue-%d-token".formatted(boardIdx);
			String token = queueTokenUtil.generateToken(boardIdx, userIdx);

			ResponseCookie cookie = ResponseCookie.from(key, token)
					.maxAge(Duration.ofSeconds(300))
					.path("/")
					.build();
			response.addHeader("Set-Cookie", cookie.toString());
			return new BaseResponse(BaseResponseStatus.SUCCESS);
		}

		Long rank = queueService.getRank(boardIdx, userIdx);
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
