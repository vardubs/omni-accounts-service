package com.varundublish.omnirio.accountsservice.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", allocationSize = 1)
    private Long id;

    @Column(name = "ACCOUNT_ID", updatable = false, nullable = false, unique = true)
    private String accountId;

    @NotNull(message = "accountType cannot be null")
    private String accountType;

    private LocalDate openDate;

    private String customerId;

    private String customerName;

    @NotNull(message = "branch cannot be null")
    private String branch;

    private Character minorIndicator;

}
