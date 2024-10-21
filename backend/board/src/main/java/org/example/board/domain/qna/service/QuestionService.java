package org.example.backend.domain.qna.service;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.board.repository.ProductBoardRepository;
import org.example.board.domain.qna.model.dto.QuestionDto;
import org.example.board.domain.qna.model.entity.Question;
import org.example.board.domain.qna.repository.AnswerRepository;
import org.example.board.domain.qna.repository.QuestionRepository;
import org.example.board.domain.user.model.entity.User;
import org.example.board.domain.user.repository.UserRepository;
import org.example.board.global.common.constants.AnswerStatus;
import org.example.board.global.common.constants.BaseResponseStatus;
import org.example.board.global.exception.InvalidCustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final ProductBoardRepository productBoardRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

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

        return question.toCreateResponse();  // 엔티티의 변환 메서드 사용
    }

    public Page<QuestionDto.QuestionListResponse> getQuestionsByProductBoardIdx(Long productBoardIdx, Pageable pageable) {
        return questionRepository.findByProductBoard_Idx(productBoardIdx, pageable).map(Question::toListResponse);
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

    public Page<QuestionDto.QuestionListResponse> getQuestionsByUserEmail(String userEmail, Pageable pageable) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_USER_NOT_FOUND));

        return questionRepository.findByUser(user, pageable)
                .map(Question::toListResponse);
    }

    public void updateQuestion(Long id, QuestionDto.QuestionUpdateRequest request, String email) {
        // 수정할 문의 조회
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_QUESTION_UPDATE_FAIL_NOT_FOUND));

        // 문의 작성자와 로그인된 사용자의 이메일이 동일한지 확인
        if (!question.getUser().getEmail().equals(email)) {
            throw new InvalidCustomException(BaseResponseStatus.FAIL_UNAUTHORIZED);
        }

        // 문의 내용 업데이트
        question.updateContent(request.getTitle(), request.getContent());
        questionRepository.save(question);  // 수정된 문의 저장
    }

    public Page<QuestionDto.QuestionListResponse> getQuestionsByCompanyBoard(String companyEmail, Pageable pageable) {
        List<ProductBoard> productBoards = productBoardRepository.findByCompanyEmail(companyEmail);
        if (productBoards.isEmpty()) {
            return Page.empty(pageable);
        }

        return questionRepository.findByProductBoardIn(productBoards, pageable)
                .map(Question::toListResponse);
    }
}
