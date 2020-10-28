package com.varundublish.omnirio.accountsservice.model.response;

import com.varundublish.omnirio.accountsservice.model.entities.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountResponse {
    private Account account;
    private UserResponse customer;
}
