package org.example.backend.domain.qna.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.repository.ProductBoardRepository;
import org.example.backend.domain.qna.model.dto.QuestionDto;
import org.example.backend.domain.qna.model.entity.Question;
import org.example.backend.domain.qna.repository.QuestionRepository;
import org.example.backend.domain.user.model.entity.User;
import org.example.backend.domain.user.repository.UserRepository;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ProductBoardRepository productBoardRepository;
    private final UserRepository userRepository;

    public QuestionDto.QuestionCreateResponse createQuestion(QuestionDto.QuestionCreateRequest request) {
        // 사용자 조회
        User user = userRepository.findById(request.getUserIdx())
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_USER_NOT_FOUND));

        // 상품 게시판 조회
        ProductBoard productBoard = productBoardRepository.findById(request.getProductBoardIdx())
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_PRODUCT_BOARD_NOT_FOUND));

        // 문의 생성
        Question question = request.toEntity(user, productBoard);
        questionRepository.save(question);

        // 문의 등록 후 사용자 이름, 답변 상태, 등록 날짜 함께 반환
        return QuestionDto.QuestionCreateResponse.builder()
                .idx(question.getIdx())
                .title(question.getTitle())
                .content(question.getContent())
                .userName(user.getName())  // 사용자 이름 반환
                .answerStatus(question.getAnswerStatus())  // 답변 상태 반환
                .createdAt(question.getCreatedAt())  // 생성 날짜 반환
                .build();
    }
    public List<QuestionDto.QuestionListResponse> getQuestions() {
        return questionRepository.findAll().stream()
                .map(question -> QuestionDto.QuestionListResponse.builder()
                        .idx(question.getIdx())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .userName(question.getUser().getName())  // 사용자 이름
                        .answerStatus(question.getAnswerStatus())  // 답변 상태
                        .createdAt(question.getCreatedAt())  // 생성일
                        .build())
                .collect(Collectors.toList());
    }
}
