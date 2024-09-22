package org.example.backend.domain.qna.repository;

import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.qna.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByProductBoardIn(List<ProductBoard> productBoards);
}
