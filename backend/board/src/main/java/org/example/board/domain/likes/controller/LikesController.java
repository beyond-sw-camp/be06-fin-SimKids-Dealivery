package org.example.board.domain.likes.controller;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.likes.model.dto.LikesDto;
import org.example.board.domain.likes.service.LikesService;
import org.example.board.global.common.constants.BaseResponse;
import org.example.board.global.common.constants.BaseResponseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping("")
    public BaseResponse like(
            @RequestBody LikesDto.LikeRequest request,
            @RequestHeader("X-User-Idx") Long userIdx
            ){
        likesService.like(request, userIdx);
        return new BaseResponse();
    }

    @GetMapping("/list")
    public BaseResponse list(@RequestHeader("X-User-Idx") Long userIdx, @RequestParam(value = "page", defaultValue = "1")Integer page) {
        Pageable pageable = PageRequest.of(page - 1, 4);
        if (userIdx == null) {
            return new BaseResponse(BaseResponseStatus.FAIL);
        }
        Page<LikesDto.LikeResponse> response = likesService.getLikes(userIdx, pageable);
        return new BaseResponse(response);
    }
}
