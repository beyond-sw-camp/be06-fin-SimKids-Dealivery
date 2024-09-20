package org.example.backend.domain.qna.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.domain.qna.model.dto.QuestionDto;
import org.example.backend.domain.qna.service.QuestionService;
import org.example.backend.global.common.constants.BaseResponse;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.common.constants.SwaggerDescription;
import org.example.backend.global.common.constants.SwaggerExamples;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qna/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @Operation(summary = "문의 등록 API", description = SwaggerDescription.QNA_QUESTION_REQUEST,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = SwaggerExamples.QNA_QUESTION_REQUEST)
                            }
                    )
            ))

    @PostMapping("/create")
    public BaseResponse create(@RequestBody QuestionDto.QuestionCreateRequest request, @AuthenticationPrincipal UserDetails userDetails){
        try{
            String email = userDetails.getUsername(); // 인증된 사용자의 이메일 가져오기

            if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
                throw new InvalidCustomException(BaseResponseStatus.QNA_QUESTION_FAIL_EMPTY_TITLE);
            }
            if (request.getContent() == null || request.getContent().trim().isEmpty()) {
                throw new InvalidCustomException(BaseResponseStatus.QNA_QUESTION_FAIL_EMPTY_CONTENT);
            }

            Long productBoardIdx = request.getProductBoardIdx();  // Request Body로 전달받은 productBoardIdx 사용

            // 문의 등록 후 사용자 이름, 날짜, 답변 상태를 함께 응답
            QuestionDto.QuestionCreateResponse response = questionService.createQuestion(request, email, productBoardIdx);
            return new BaseResponse<>(response);

        } catch (InvalidCustomException e){
            return new  BaseResponse<>(e.getStatus());
        } catch (Exception e){
            return new BaseResponse<>(BaseResponseStatus.FAIL);
        }
    }

    @Operation(summary = "문의 목록 조회 API", description = "DB에 저장된 문의 목록을 반환합니다.")
    @GetMapping("/list")
    public BaseResponse<List<QuestionDto.QuestionListResponse>> getQuestions() {
        List<QuestionDto.QuestionListResponse> questionList = questionService.getQuestions();
        return new BaseResponse<>(questionList);
    }
}
