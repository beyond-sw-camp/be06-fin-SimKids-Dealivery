package org.example.backend.domain.qna.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.repository.ProductBoardRepository;
import org.example.backend.domain.qna.model.dto.QuestionDto;
import org.example.backend.domain.qna.model.entity.Question;
import org.example.backend.domain.qna.repository.QuestionRepository;
import org.example.backend.domain.user.model.entity.User;
import org.example.backend.domain.user.repository.UserRepository;
import org.example.backend.global.common.constants.AnswerStatus;
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

    public QuestionDto.QuestionCreateResponse createQuestion(QuestionDto.QuestionCreateRequest request, String email, Long productBoardIdx) {
        // 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_USER_NOT_FOUND));

        // 상품 게시판 조회
        ProductBoard productBoard = productBoardRepository.findById(productBoardIdx)
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
                        .email(question.getUser().getEmail())
                        .build())
                .collect(Collectors.toList());
    }
    public void deleteQuestion(Long questionId, String email){
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_ANSWER_DELETE_FAIL_NOT_FOUND));

        // 문의 작성자와 로그인된 사용자의 이메일이 동일한지 확인
        if (!question.getUser().getEmail().equals(email)) {
            throw new InvalidCustomException(BaseResponseStatus.FAIL_UNAUTHORIZED);
        }

        // 답변 상태가 "답변대기"인지 확인
        if (!question.getAnswerStatus().equals(AnswerStatus.ANSWER_WAITING.getStatus())) {
            throw new InvalidCustomException(BaseResponseStatus.QNA_ANSWER_DELETE_FAIL);
        }

        // 삭제 처리
        questionRepository.delete(question);
    }
}
