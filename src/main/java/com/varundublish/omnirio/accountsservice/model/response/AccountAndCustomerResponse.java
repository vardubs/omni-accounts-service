package com.varundublish.omnirio.accountsservice.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AccountAndCustomerResponse {
    private List<AccountResponse> account;
    private UserResponse customer;
}
