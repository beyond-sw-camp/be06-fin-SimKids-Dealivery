package org.example.user.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.example.backend.domain.delivery.model.entity.Delivery;
//import org.example.backend.domain.likes.model.entity.Likes;
//import org.example.backend.domain.qna.model.entity.Question;
import org.example.user.domain.delivery.model.entity.Delivery;
import org.example.user.domain.user.model.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<Delivery> deliveries;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<Question> questions = new ArrayList<>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<Likes> likes;

    public void deductPoints(Long point) {
        this.point -= point;
    }

    public void earnPoints(Long point) {
        this.point += point;
    }

    public UserDto.UserDetailResponse toUserDetailResponse(){
        return UserDto.UserDetailResponse.builder()
                .userIdx(this.idx)
                .name(this.name)
                .email(this.email)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .postNumber(this.postNumber)
                .phoneNumber(this.phoneNumber)
                .deliveries(this.deliveries.stream().map(Delivery::toDeliveryResponse).collect(Collectors.toList()))
                .point(this.point)
                .build();
    }

    public void editDetail(UserDto.UserDetailEditRequest request){
        this.address = request.getAddress();
        this.addressDetail = request.getAddressDetail();
        this.phoneNumber = request.getPhoneNumber();
        this.postNumber = request.getPostNumber();
    }

    public UserDto.UserSignupComplete toUserSignupComplete(){
        return UserDto.UserSignupComplete.builder()
                .userIdx(this.idx)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .point(this.point)
                .role(this.role)
                .type(this.type)
                .build();
    }

    public UserDto.AuthUserNotFoundResponse toAuthUserNotFoundResponse(){
        return UserDto.AuthUserNotFoundResponse.builder()
                .idx(this.idx)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .type(this.type)
                .build();
    }
}
