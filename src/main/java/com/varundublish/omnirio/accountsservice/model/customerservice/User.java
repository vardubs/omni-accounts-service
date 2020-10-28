package com.varundublish.omnirio.accountsservice.model.customerservice;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private Long userId;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Character gender;
    private String phoneNumber;
    private Role role;
    private boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                username.equals(user.username) &&
                dateOfBirth.equals(user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, dateOfBirth);
    }
}
