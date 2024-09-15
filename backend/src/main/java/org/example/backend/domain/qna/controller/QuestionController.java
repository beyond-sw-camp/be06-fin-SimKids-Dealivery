package org.example.backend.domain.qna.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.domain.qna.model.dto.QuestionDto;
import org.example.backend.domain.qna.service.QuestionService;
import org.example.backend.global.common.constants.BaseResponse;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qna/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/create")
    public BaseResponse create(@Valid @RequestBody QuestionDto.QuestionCreateRequest request){
        try{
            questionService.createQuestion(request);
            return new BaseResponse(BaseResponseStatus.SUCCESS);
        } catch (InvalidCustomException e){
            return new BaseResponse(e.getStatus());
        } catch (Exception e){
            return new BaseResponse(BaseResponseStatus.FAIL);
        }
    }
}
