package org.example.backend.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.qna.model.entity.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.backend.domain.likes.model.entity.Likes;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private Boolean status;

    private String postNumber;

    private String address;

    private String addressDetail;

    private Long point;

    private Boolean emailStatus;

    private String type;

    private LocalDateTime registeredAt;

    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Delivery> deliveryList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Likes> likes;
}
