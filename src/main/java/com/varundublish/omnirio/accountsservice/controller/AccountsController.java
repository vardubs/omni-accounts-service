package com.varundublish.omnirio.accountsservice.controller;

import com.varundublish.omnirio.accountsservice.model.request.AccountOpeningRequest;
import com.varundublish.omnirio.accountsservice.model.response.AccountAndCustomerResponse;
import com.varundublish.omnirio.accountsservice.model.response.AccountResponse;
import com.varundublish.omnirio.accountsservice.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Controller for Accounts Manager
 *
 */
@RestController
@RequestMapping("/api/v1")
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @GetMapping("/accounts/myaccounts")
    public ResponseEntity<?> getMyAccountDetails(@RequestHeader("Authorization") String authToken){
        AccountAndCustomerResponse response =  accountsService.getMyAccountDetails(authToken);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/bm/accounts/{accountId}")
    public ResponseEntity<?> getCustomerAccountDetails(@RequestHeader("Authorization") String authToken, @PathVariable("accountId") String accountId){

        AccountAndCustomerResponse accountResponse = accountsService.getCustomerAccountDetails(authToken, accountId);

        EntityModel<AccountAndCustomerResponse> resource = EntityModel.of(accountResponse);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerAccountDetails(authToken,  accountResponse.getCustomer().getUserId())).withRel("customer-details"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteAccount(authToken,  accountResponse.getAccount().get(0).getAccountId())).withRel("delete-account"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PostMapping("/bm/accounts/open")
    public ResponseEntity<?>  openNewAccount(@RequestHeader("Authorization") String authToken, @Valid @RequestBody AccountOpeningRequest request){
        AccountResponse accountResponse =  accountsService.openAccount(authToken, request);
        EntityModel<AccountResponse> resource = EntityModel.of(accountResponse);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getCustomerAccountDetails(authToken,  accountResponse.getCustomerId())).withRel("customer-details"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteAccount(authToken,  accountResponse.getAccountId())).withRel("delete-account"));
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @DeleteMapping("/bm/accounts/{accountId}")
    public ResponseEntity<?>  deleteAccount(@RequestHeader("Authorization") String authToken, @PathVariable("accountId") String accountId){
        //TODO Sample Response
        String response =  "Account Deleted !";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
