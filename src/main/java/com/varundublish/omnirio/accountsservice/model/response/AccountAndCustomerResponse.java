package com.varundublish.omnirio.accountsservice.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountAndCustomerResponse {
    private AccountResponse account;
    private UserResponse customer;
}
