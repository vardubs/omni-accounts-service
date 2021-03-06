package com.varundublish.omnirio.accountsservice.client;

import com.varundublish.omnirio.accountsservice.model.request.UserRequest;
import com.varundublish.omnirio.accountsservice.model.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Feign Client for Customer Service
 */
@FeignClient(value = "customer-service")
public interface CustomerServiceFeignClient {

    /**
     * Test Url method
     * @return
     */
    @GetMapping("/test")
    public String testUrl();


    @PostMapping("/api/v1/bm/customers")
    UserResponse addNewCustomer(@RequestHeader("Authorization") String authorizationToken, UserRequest newCustomer);

    @GetMapping("/api/v1/bm/customers/{customerId}")
    UserResponse getExistingCustomer(@RequestHeader("Authorization") String authorizationToken, @PathVariable("customerId") String customerId);

    @GetMapping("/api/v1/customers/me")
    UserResponse getMyDetails(@RequestHeader("Authorization") String authorizationToken);
}
