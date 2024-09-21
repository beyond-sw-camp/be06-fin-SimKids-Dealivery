package org.example.backend.domain.qna.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.user.model.entity.User;
import org.example.backend.global.common.constants.AnswerStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "answer_status")
    private String answerStatus = AnswerStatus.ANSWER_WAITING.getStatus();

    // 답변이 등록 되면 문의 상태를 "답변완료"로 변경하는 메서드
    public void markAsAnswered(){
        this.answerStatus = AnswerStatus.ANSWER_COMPLETED.getStatus();
    }

    // 답변 상태를 "답변 대기"로 변경하는 메서드
    public void markAsWaiting() {
        this.answerStatus = AnswerStatus.ANSWER_WAITING.getStatus();
    }

    // 문의 수정 시에만 수정 시간(modifiedAt) 갱신
    public void updateContent(String newContent) {
        this.content = newContent;
        this.modifiedAt = LocalDateTime.now();  // 문의가 수정될 때만 modifiedAt 갱신
    }
}
