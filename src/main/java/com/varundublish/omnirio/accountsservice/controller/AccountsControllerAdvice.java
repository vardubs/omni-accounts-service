package com.varundublish.omnirio.accountsservice.controller;

import com.varundublish.omnirio.accountsservice.exceptions.AccountServiceException;
import com.varundublish.omnirio.accountsservice.exceptions.CustomAccountErrorResponse;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class AccountsControllerAdvice {

    //Todo to create more Exception Handler based on various Exceptions at different services

    private static final Logger LOGGER = LogManager.getLogger(AccountsControllerAdvice.class);

    @ExceptionHandler(value = {AccountServiceException.class, UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomAccountErrorResponse> handleAccountServiceException(Exception e) {

        CustomAccountErrorResponse customErrorResponse = new CustomAccountErrorResponse();
        customErrorResponse.setMessage(e.getLocalizedMessage());
        customErrorResponse.setStatus("500");

        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomAccountErrorResponse> handleAnyExceptions(Exception e) {

        CustomAccountErrorResponse customErrorResponse = new CustomAccountErrorResponse();

        customErrorResponse.setMessage(e.getLocalizedMessage());
        customErrorResponse.setStatus("500");

        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
