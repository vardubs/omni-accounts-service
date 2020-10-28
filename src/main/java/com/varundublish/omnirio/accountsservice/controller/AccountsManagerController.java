package com.varundublish.omnirio.accountsservice.controller;

import com.varundublish.omnirio.accountsservice.model.entities.Account;
import com.varundublish.omnirio.accountsservice.model.request.AccountOpeningRequest;
import com.varundublish.omnirio.accountsservice.model.response.AccountResponse;
import com.varundublish.omnirio.accountsservice.service.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller for Accounts Manager
 *
 */
@RestController
@RequestMapping("/bm")
public class AccountsManagerController {

    @Autowired
    private AccountManagerService accountManagerService;

    @GetMapping("/accounts/{accountId}")
    public AccountResponse getAccountDetails(@RequestHeader("Authorization") String authToken, @PathVariable("accountId") Long accountId){
        return accountManagerService.getAccountDetails(authToken, accountId);
    }

    @PostMapping("/accounts/open")
    public Account openNewAccount(@RequestHeader("Authorization") String authToken, @RequestBody AccountOpeningRequest request){
        return accountManagerService.openAccount(authToken, request);
    }
}
