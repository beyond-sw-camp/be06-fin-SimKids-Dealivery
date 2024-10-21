package org.example.auth.global.adaptor.out;

import org.example.auth.domain.user.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8000/api", contextId = "userServiceClientForAuth")
public interface UserServiceClient {

    @GetMapping("/user/user-email")
    User getUserByEmail(@RequestParam("email") String email);
}
