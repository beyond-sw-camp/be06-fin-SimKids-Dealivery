package org.example.board.global.adaptor.out;

import org.example.board.domain.company.model.entity.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8000/api", contextId = "userServiceClientForBoard")
public interface UserServiceClient {

    @GetMapping("/company/company-idx")
    Company getCompany(@RequestParam("companyIdx") Long companyidx);  // companyIdx를 가져오는 API 호출
}
