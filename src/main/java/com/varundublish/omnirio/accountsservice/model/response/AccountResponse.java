package com.varundublish.omnirio.accountsservice.model.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountResponse implements Serializable {

    private String accountId;
    private String accountType;
    private LocalDate openDate;
    private Long customerId;
    private String customerName;
    private String branch;
    private Character minorIndicator;

}
