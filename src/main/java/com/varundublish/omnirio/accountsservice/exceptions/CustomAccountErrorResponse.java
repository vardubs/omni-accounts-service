package com.varundublish.omnirio.accountsservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CustomAccountErrorResponse {


    private String status;
    private String message;
    private LocalDateTime timeStamp = LocalDateTime.now();

}
