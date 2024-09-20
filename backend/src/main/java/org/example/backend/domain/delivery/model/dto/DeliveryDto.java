package org.example.backend.domain.delivery.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.delivery.model.entity.Delivery;
import org.example.backend.domain.user.model.entity.User;

public class DeliveryDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DeliveryResponse{
        private Long idx;

        private String name;

        private Boolean isDefault;

        private String address;

        private String addressDetail;

        private String postNumber;

    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateDeliveryRequest{

        private String name;

        private Boolean isDefault;

        private String address;

        private String addressDetail;

        private String postNumber;

        public Delivery toEntity(User user){
            return Delivery.builder()
                    .address(this.address)
                    .name(this.name)
                    .addressDetail(this.addressDetail)
                    .isDefault(this.isDefault)
                    .postNumber(this.postNumber)
                    .user(user)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SetDefaultRequest{
        private Long idx;
    }
}
