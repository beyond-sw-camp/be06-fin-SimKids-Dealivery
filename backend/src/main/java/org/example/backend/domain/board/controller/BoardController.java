package org.example.backend.domain.board.controller;

import org.example.backend.domain.board.model.dto.BoardDto;
import org.example.backend.domain.board.service.ProductBoardService;
import org.example.backend.global.common.constants.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	private final ProductBoardService productBoardService;

	@RequestMapping(value = "/product-boards", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.MULTIPART_FORM_DATA_VALUE})
	public BaseResponse create(@RequestPart("boardCreateRequest") BoardDto.BoardCreateRequest boardCreateRequest,
		@RequestPart("productThumbnails") MultipartFile[] productThumbnails,
		@RequestPart("productDetail") MultipartFile productDetail) {
		productBoardService.create(boardCreateRequest, productThumbnails, productDetail);
		return new BaseResponse();
	}
}
