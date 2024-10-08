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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

        return question.toCreateResponse();  // 엔티티의 변환 메서드 사용
    }

    public Page<QuestionDto.QuestionListResponse> getQuestionsByProductBoardIdx(Long productBoardIdx, Pageable pageable) {
        Page<Question> questions = questionRepository.findByProductBoard_Idx(productBoardIdx, pageable);
        questions.forEach(this::loadAnswers); // 별도의 메서드로 필요 시 답변을 로딩

        return questions.map(Question::toListResponse);
    }

    private void loadAnswers(Question question) {
        // 지연 로딩된 answers를 필요할 때 불러오는 방식
        question.getAnswers().size();
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
        Page<Question> questions = questionRepository.findByUser(user, pageable);
        questions.forEach(this::loadAnswers);

        return questions.map(Question::toListResponse);
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

        Page<Question> questions = questionRepository.findByProductBoardIn(productBoards, pageable);
        questions.forEach(this::loadAnswers);

        return questions.map(Question::toListResponse);
    }
}
