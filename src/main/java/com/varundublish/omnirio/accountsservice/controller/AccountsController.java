package com.varundublish.omnirio.accountsservice.controller;

import com.varundublish.omnirio.accountsservice.model.request.AccountOpeningRequest;
import com.varundublish.omnirio.accountsservice.model.response.AccountAndCustomerResponse;
import com.varundublish.omnirio.accountsservice.model.response.AccountResponse;
import com.varundublish.omnirio.accountsservice.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller for Accounts Manager
 *
 */
@RestController
@RequestMapping("/api")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;


    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/accounts/{accountId}")
    public AccountAndCustomerResponse getMyAccountDetails(@RequestHeader("Authorization") String authToken, @PathVariable("accountId") Long accountId){
        return accountsService.getMyAccountDetails(authToken, accountId);
    }

    @PreAuthorize("hasRole('BRANCH-MANAGER')")
    @GetMapping("/bm/accounts/{accountId}")
    public AccountAndCustomerResponse getCustomerAccountDetails(@RequestHeader("Authorization") String authToken, @PathVariable("accountId") Long accountId){
        return accountsService.getCustomerAccountDetails(authToken, accountId);
    }

    @PreAuthorize("hasRole('BRANCH-MANAGER')")
    @PostMapping("/bm/accounts/open")
    public AccountResponse openNewAccount(@RequestHeader("Authorization") String authToken, @Valid @RequestBody AccountOpeningRequest request){
        return accountsService.openAccount(authToken, request);
    }


}
