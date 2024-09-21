package org.example.backend.domain.qna.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.qna.model.dto.AnswerDto;
import org.example.backend.domain.qna.model.entity.Answer;
import org.example.backend.domain.qna.model.entity.Question;
import org.example.backend.domain.qna.repository.AnswerRepository;
import org.example.backend.domain.qna.repository.QuestionRepository;
import org.example.backend.domain.company.model.entity.Company;
import org.example.backend.domain.company.repository.CompanyRepository;
import org.example.backend.global.common.constants.AnswerStatus;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final CompanyRepository companyRepository;

    public void createAnswer(AnswerDto.AnswerCreateRequest request, String email) {
        // 기업회원 조회
        Company company = companyRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_ANSWERS_FAIL));

        // 문의 조회
        Question question = questionRepository.findById(request.getQuestionIdx())
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_ANSWER_FAIL_NOT_FOUND));

        // 해당 문의가 달린 게시글(ProductBoard)을 조회
        ProductBoard productBoard = question.getProductBoard();

        // 기업회원이 이 게시글(ProductBoard)의 작성자인지 확인
        if (!productBoard.getCompany().getEmail().equals(company.getEmail())) {
            throw new InvalidCustomException(BaseResponseStatus.FAIL_UNAUTHORIZED);
        }

        // 답변 생성 및 저장
        Answer answer = request.toEntity(company, question);
        answerRepository.save(answer);

        // 문의 상태를 "답변 완료"로 변경
        question.markAsAnswered();
        questionRepository.save(question);
    }
}
