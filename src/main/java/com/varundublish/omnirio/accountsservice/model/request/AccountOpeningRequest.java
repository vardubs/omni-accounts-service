package com.varundublish.omnirio.accountsservice.model.request;

import com.varundublish.omnirio.accountsservice.model.entities.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountOpeningRequest {

    @NotNull(message = "account cannot be null")
    Account account;

    Long customerId;

    @NotNull(message = "existingCustomer cannot be null")
    Boolean existingCustomer;

    UserRequest newCustomer;

    public Boolean isExistingCustomer() {
        return existingCustomer;
    }

}
