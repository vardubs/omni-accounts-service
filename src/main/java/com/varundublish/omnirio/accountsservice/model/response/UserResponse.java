package com.varundublish.omnirio.accountsservice.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserResponse {

    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Character gender;
    private String phoneNumber;
    private String role;

}
