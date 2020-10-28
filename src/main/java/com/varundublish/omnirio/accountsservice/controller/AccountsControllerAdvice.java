package com.varundublish.omnirio.accountsservice.controller;

import com.varundublish.omnirio.accountsservice.exceptions.AccountServiceException;
import com.varundublish.omnirio.accountsservice.exceptions.CustomAccountErrorResponse;
import lombok.extern.java.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class AccountsControllerAdvice {

    private static final Logger LOGGER = LogManager.getLogger(AccountsControllerAdvice.class);

    @ExceptionHandler(value = AccountServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomAccountErrorResponse> handleAccountServiceException(Exception e) {

        LOGGER.error(e.getMessage());
        CustomAccountErrorResponse customErrorResponse = new CustomAccountErrorResponse();
        customErrorResponse.setMessage(e.getMessage());
        customErrorResponse.setStatus("400");

        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomAccountErrorResponse> handleExceptions(Exception e) {

        LOGGER.error(e.getMessage());
        CustomAccountErrorResponse customErrorResponse = new CustomAccountErrorResponse();

        customErrorResponse.setMessage("INTERNAL_SERVER_ERROR");
        customErrorResponse.setStatus("500");

        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
