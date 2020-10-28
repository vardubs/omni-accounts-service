package com.varundublish.omnirio.accountsservice.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRequest {

    private Long userId;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Character gender;
    private String phoneNumber;
    private String role;


}
