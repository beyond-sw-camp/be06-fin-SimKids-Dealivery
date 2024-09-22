package org.example.backend.domain.qna.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.qna.model.dto.QuestionDto;
import org.example.backend.domain.user.model.entity.User;
import org.example.backend.global.common.constants.AnswerStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_board_idx")
    private ProductBoard productBoard;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "answer_status")
    private String answerStatus = AnswerStatus.ANSWER_WAITING.getStatus();

    public QuestionDto.QuestionListResponse toQuestionListResponse() {
        return QuestionDto.QuestionListResponse.builder()
                .idx(this.idx)
                .title(this.title)
                .content(this.content)
                .userName(this.user.getName())
                .answerStatus(this.answerStatus)
                .createdAt(this.createdAt)
                .email(this.user.getEmail())
                .build();
    }

    // 새로운 변환 메서드: 문의 생성 응답 DTO로 변환
    public QuestionDto.QuestionCreateResponse toQuestionCreateResponse() {
        return QuestionDto.QuestionCreateResponse.builder()
                .idx(this.idx)
                .title(this.title)
                .content(this.content)
                .userName(this.user.getName())
                .answerStatus(this.answerStatus)
                .createdAt(this.createdAt)
                .build();
    }

    // 답변이 등록되면 문의 상태를 "답변완료"로 변경하는 메서드
//    public void markAsAnswered(){
//        this.answerStatus = AnswerStatus.ANSWER_COMPLETED.getStatus();
//    }
}
