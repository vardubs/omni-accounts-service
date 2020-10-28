package com.varundublish.omnirio.accountsservice.controller;

import com.varundublish.omnirio.accountsservice.model.response.AccountResponse;
import com.varundublish.omnirio.accountsservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Account Customer Service
 */
@RestController

public class AccountsCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/accounts/{accountId}")
    public AccountResponse getAccountDetails(@RequestHeader("Authorization") String authToken, @PathVariable("accountId") Long accountId){
        return customerService.getAccountDetails(authToken, accountId);
    }
}
