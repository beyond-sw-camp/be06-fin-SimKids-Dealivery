package org.example.backend.domain.board.controller;

import org.example.backend.domain.board.model.dto.ProductBoardDto;
import org.example.backend.domain.board.service.ProductBoardService;
import org.example.backend.global.common.constants.BaseResponse;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.common.constants.BoardStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.example.backend.global.security.custom.model.dto.CustomCompanyDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-boards")
public class BoardController {
	private final ProductBoardService productBoardService;
	private final Integer MAIN_OPEN = 8;
	private final Integer MAIN_READY = 12;
	private final Integer USER_LIST_SIZE = 21;
	private final Integer COMPANY_LIST_SIZE = 10;

	@Operation(summary = "상품 메인 목록 조회 API")
	@GetMapping(value = "/main/list")
	public BaseResponse mainList(@RequestParam(value = "page", defaultValue = "1") Integer page,
		@RequestParam(value = "status", defaultValue = "진행 전") String status) {
		Slice<ProductBoardDto.BoardListResponse> responses;
		if (status.equals(BoardStatus.READY.getStatus())) {
			responses = productBoardService.mainList(BoardStatus.READY.getStatus(), PageRequest.of(page - 1, MAIN_READY, Sort.by(Sort.Direction.DESC, "idx")));
			return new BaseResponse(responses);
		} else if (status.equals(BoardStatus.OPEN.getStatus())) {
			responses = productBoardService.mainList(BoardStatus.OPEN.getStatus(), PageRequest.of(page - 1, MAIN_OPEN, Sort.by(Sort.Direction.DESC, "idx")));
			return new BaseResponse(responses);
		} else {
			return new BaseResponse(BaseResponseStatus.FAIL);
		}
	}

	@Operation(summary = "상품 게시글 목록 조회 API")
	@GetMapping(value = "/list")
	public BaseResponse list(Integer page, @RequestParam(required = false) String search) {
		Pageable pageable = PageRequest.of(page - 1, USER_LIST_SIZE);
		Page<ProductBoardDto.BoardListResponse> boardListResponses = productBoardService.list(search, pageable);
		return new BaseResponse(boardListResponses);
	}

	@Operation(summary = "상품 게시글 상세 조회 API")
	@GetMapping(value = "/{idx}/detail")
	public BaseResponse detail(@PathVariable Long idx) {
		ProductBoardDto.BoardDetailResponse response = productBoardService.detail(idx);
		return response == null ? new BaseResponse(BaseResponseStatus.FAIL) : new BaseResponse(response);
	}

	@Operation(summary = "판매자 회원 상품 등록 API")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.MULTIPART_FORM_DATA_VALUE})
	public BaseResponse create(@AuthenticationPrincipal CustomCompanyDetails customCompanyDetails, @Valid @RequestPart("boardCreateRequest") ProductBoardDto.BoardCreateRequest boardCreateRequest,
		@RequestPart(value = "productThumbnails") MultipartFile[] productThumbnails,
		@RequestPart(value = "productDetail") MultipartFile productDetail) {
		if (customCompanyDetails == null) {
			throw new InvalidCustomException(BaseResponseStatus.FAIL);
		}
		productBoardService.create(customCompanyDetails.getIdx(), boardCreateRequest, productThumbnails, productDetail);
		return new BaseResponse();
	}

	@Operation(summary = "판매자 회원 게시글 조회 API")
	@GetMapping(value = "/company/list")
	public BaseResponse companyList(
		@AuthenticationPrincipal CustomCompanyDetails customCompanyDetails, Integer page,
		@RequestParam(required = false) String status, @RequestParam(required = false) Integer month) {
		if (customCompanyDetails == null) {
			throw new InvalidCustomException(BaseResponseStatus.FAIL);
		}
		Pageable pageable = PageRequest.of(page - 1, COMPANY_LIST_SIZE);
		BoardStatus boardStatus = status == null ? null : BoardStatus.from(status);
		Page<ProductBoardDto.CompanyBoardListResponse> boardListResponses = productBoardService.companyList(customCompanyDetails.getIdx(), boardStatus == null ? null : boardStatus.getStatus(), month, pageable);
		return new BaseResponse(boardListResponses);
	}

	/* TODO
	@AuthenticationPrincipal 적용하면, Company Idx를 이용해 ProductBoard 필터링하는 로직 추가 되어야 함
	* */
	@Operation(summary = "판매자 회원 게시글 상세 조회 API")
	@GetMapping(value = "/company/{idx}/detail")
	public BaseResponse companyDetail(@AuthenticationPrincipal CustomCompanyDetails customCompanyDetails, @PathVariable Long idx) {
		if (customCompanyDetails == null) {
			throw new InvalidCustomException(BaseResponseStatus.FAIL);
		}
		ProductBoardDto.CompanyBoardDetailResponse response =  productBoardService.getCompanyDetail(customCompanyDetails.getIdx(), idx);
		return response == null ? new BaseResponse(BaseResponseStatus.FAIL) : new BaseResponse(response);
	}
}
