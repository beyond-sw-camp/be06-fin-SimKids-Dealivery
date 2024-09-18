package org.example.backend.domain.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.delivery.model.entity.Delivery;
import org.example.backend.domain.user.model.dto.UserDto;

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

    public UserDto.UserDetailResponse toUserDetailResponse(){
        return UserDto.UserDetailResponse.builder()
                .name(this.name)
                .email(this.email)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .postNumber(this.postNumber)
                .phoneNumber(this.phoneNumber)
                .deliveries(this.deliveries.stream().map(Delivery::toDeliveryResponse).collect(Collectors.toList()))
                .build();
    }
}
