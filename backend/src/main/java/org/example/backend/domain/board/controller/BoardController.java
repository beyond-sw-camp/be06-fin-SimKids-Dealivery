package org.example.backend.domain.board.controller;

import org.example.backend.domain.board.model.dto.BoardDto;
import org.example.backend.domain.board.service.ProductBoardService;
import org.example.backend.global.common.constants.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-boards")
public class BoardController {
	private final ProductBoardService productBoardService;
	private final Integer SIZE = 10;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.MULTIPART_FORM_DATA_VALUE})
	public BaseResponse create(@RequestPart("boardCreateRequest") BoardDto.BoardCreateRequest boardCreateRequest,
		@RequestPart("productThumbnails") MultipartFile[] productThumbnails,
		@RequestPart("productDetail") MultipartFile productDetail) {
		productBoardService.create(boardCreateRequest, productThumbnails, productDetail);
		return new BaseResponse();
	}

	@Operation(summary = "판매자 회원 게시글 조회 API")
	@GetMapping(value = "/company/list")
	public BaseResponse list(Integer page) {
		Pageable pageable = PageRequest.of(page, SIZE, Sort.Direction.DESC, "idx");
		Page<BoardDto.BoardListResponse> boardListResponses = productBoardService.list(pageable);
		return new BaseResponse(boardListResponses);
	}
}
