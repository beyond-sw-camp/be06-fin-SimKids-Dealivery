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
        Company company = companyRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_ANSWERS_FAIL));

        Question question = questionRepository.findById(request.getQuestionIdx())
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_ANSWER_FAIL_NOT_FOUND));

        if (!question.getProductBoard().getCompany().getEmail().equals(company.getEmail())) {
            throw new InvalidCustomException(BaseResponseStatus.FAIL_UNAUTHORIZED);
        }

        Answer answer = request.toEntity(company, question);
        answerRepository.save(answer);

        question.markAsAnswered();
        questionRepository.save(question);
    }

    public void deleteAnswer(Long answerIdx, String email) {
        Answer answer = answerRepository.findById(answerIdx)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.QNA_ANSWER_FAIL_NOT_FOUND));

        if (!answer.getCompany().getEmail().equals(email)) {
            throw new InvalidCustomException(BaseResponseStatus.FAIL_UNAUTHORIZED);
        }

        answerRepository.delete(answer);
    }
}
