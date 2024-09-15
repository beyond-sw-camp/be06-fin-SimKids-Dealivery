package org.example.backend.domain.qna.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.qna.model.entity.Question;
import org.example.backend.domain.user.model.entity.User;

import java.time.LocalDateTime;

public class QuestionDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionCreateRequest{
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotNull
        private Long userIdx;
        @NotNull
        private Long productBoardIdx;

        public Question toEntity(User user, ProductBoard productBoard) {
            return Question.builder()
                    .title(this.title)
                    .content(this.content)
                    .user(user)
                    .productBoard(productBoard)
                    .createdAt(LocalDateTime.now())
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class QuestionListResponse{
        private Long idx;
        private String title;
        private String content;
        private String userName;
        private String answerStatus;
        private LocalDateTime createdAt;
    }
}
