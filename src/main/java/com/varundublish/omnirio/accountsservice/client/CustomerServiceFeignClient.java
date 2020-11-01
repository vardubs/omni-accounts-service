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


    @PostMapping("/bm/customers")
    UserResponse addNewCustomer(@RequestHeader("Authorization") String authorizationToken, UserRequest newCustomer);

    @GetMapping("/bm/customers/{customerId}")
    UserResponse getExistingCustomer(@RequestHeader("Authorization") String authorizationToken, @PathVariable("customerId") Long customerId);

    @GetMapping("/customers/me")
    UserResponse getMyDetails(@RequestHeader("Authorization") String authorizationToken);
}
