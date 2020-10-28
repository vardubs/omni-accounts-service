package com.varundublish.omnirio.accountsservice.model.request;

import com.varundublish.omnirio.accountsservice.model.entities.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AccountOpeningRequest {
    Account account;
    Long customerId;
    Boolean existingCustomer;
    UserRequest newCustomer;

    public Boolean isExistingCustomer() {
        return existingCustomer;
    }

}
