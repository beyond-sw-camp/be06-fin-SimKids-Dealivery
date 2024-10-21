package org.example.board.domain.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.board.domain.board.model.dto.ProductBoardDto;
import org.example.board.domain.board.product.model.dto.ProductDto;
import org.example.board.domain.board.service.ProductBoardService;
import org.example.board.global.common.constants.BaseResponse;
import org.example.board.global.common.constants.BaseResponseStatus;
import org.example.board.global.common.constants.BoardStatus;
import org.example.board.global.exception.InvalidCustomException;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
	public BaseResponse mainList(@RequestHeader(value = "X-User-Idx", required = false) Long userIdx, @RequestParam(value = "page", defaultValue = "1") Integer page,
		@RequestParam(value = "status", defaultValue = "진행 전") String status) {
		if (!isValidStatus(status)) {
			return new BaseResponse(BaseResponseStatus.FAIL);
		}
		Slice<ProductBoardDto.BoardListResponse> responses;
		Pageable pageable = getPageableByStatus(status, page);
		if (userIdx == null) {
			responses = productBoardService.mainList(status, pageable);
		 }
		else {
			responses = productBoardService.mainList(userIdx, status, pageable);
		}
		return new BaseResponse(responses);
	}

	@Operation(summary = "상품 게시글 목록 조회 API")
	@GetMapping(value = "/list")
	public BaseResponse list(@RequestHeader(value = "X-User-Idx", required = false) Long userIdx, @RequestParam(value = "page", defaultValue = "1")Integer page, @RequestParam(required = false) String search) {
		Pageable pageable = PageRequest.of(page - 1, USER_LIST_SIZE);
		Page<ProductBoardDto.BoardListResponse> boardListResponses;
		if (userIdx == null) {
			boardListResponses = productBoardService.list(search, pageable);
		} else {
			boardListResponses = productBoardService.list(userIdx, search, pageable);
		}
		return new BaseResponse(boardListResponses);
	}

	@Operation(summary = "상품 게시글 상세 조회 API")
	@GetMapping(value = "/{idx}/detail")
	public BaseResponse detail(@RequestHeader(value = "X-User-Idx", required = false) Long userIdx, @PathVariable Long idx) {
		ProductBoardDto.BoardDetailResponse response;
		if (userIdx == null) {
			response = productBoardService.detail(idx);
		} else {
			response = productBoardService.detail(userIdx, idx);
		}
		return response == null ? new BaseResponse(BaseResponseStatus.FAIL) : new BaseResponse(response);
	}

	@Operation(summary = "판매자 회원 상품 등록 API")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE , MediaType.MULTIPART_FORM_DATA_VALUE})
	public BaseResponse create(@RequestHeader("X-User-Idx") Long companyIdx, @Valid @RequestPart("boardCreateRequest") ProductBoardDto.BoardCreateRequest boardCreateRequest,
		@RequestPart(value = "productThumbnails") MultipartFile[] productThumbnails,
		@RequestPart(value = "productDetail") MultipartFile productDetail) {
		if (companyIdx == null) {
			throw new InvalidCustomException(BaseResponseStatus.FAIL);
		}
		productBoardService.create(companyIdx, boardCreateRequest, productThumbnails, productDetail);
		return new BaseResponse();
	}

	@Operation(summary = "판매자 회원 게시글 조회 API")
	@GetMapping(value = "/company/list")
	public BaseResponse companyList(@RequestHeader("X-User-Idx") Long companyIdx, @RequestParam(value = "page", defaultValue = "1")Integer page,
		@RequestParam(required = false) String status, @RequestParam(required = false) Integer month) {
		if (companyIdx == null) {
			throw new InvalidCustomException(BaseResponseStatus.FAIL);
		}
		Pageable pageable = PageRequest.of(page - 1, COMPANY_LIST_SIZE);
		BoardStatus boardStatus = status == null ? null : BoardStatus.from(status);
		Page<ProductBoardDto.CompanyBoardListResponse> boardListResponses = productBoardService.companyList(companyIdx, boardStatus == null ? null : boardStatus.getStatus(), month, pageable);
		return new BaseResponse(boardListResponses);
	}

	@Operation(summary = "판매자 회원 게시글 상세 조회 API")
	@GetMapping(value = "/company/{idx}/detail")
	public BaseResponse companyDetail(@RequestHeader("X-User-Idx") Long companyIdx, @PathVariable Long idx) {
		if (companyIdx == null) {
			throw new InvalidCustomException(BaseResponseStatus.FAIL);
		}
		ProductBoardDto.CompanyBoardDetailResponse response =  productBoardService.getCompanyDetail(companyIdx, idx);
		return response == null ? new BaseResponse(BaseResponseStatus.FAIL) : new BaseResponse(response);
	}

	private boolean isValidStatus(String status) {
		return status.equals(BoardStatus.READY.getStatus()) || status.equals(BoardStatus.OPEN.getStatus());
	}

	private Pageable getPageableByStatus(String status, Integer page) {
		int pageSize = getPageSizeByStatus(status);
		return PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "idx"));
	}

	private int getPageSizeByStatus(String status) {
		if (status.equals(BoardStatus.READY.getStatus())) {
			return MAIN_READY;
		} else {
			return MAIN_OPEN;
		}
	}

	@PostMapping("/decrease/stock")
	public Boolean decreaseStock(@RequestBody List<ProductDto.OrderedProduct> orderedProducts){
		return productBoardService.decreaseStcok(orderedProducts);
	}

	@PostMapping("/restore/stock")
	public Boolean restoreStock(@RequestBody List<ProductDto.OrderedProduct> orderedProducts){
		return productBoardService.restoreStcok(orderedProducts);
	}


}
