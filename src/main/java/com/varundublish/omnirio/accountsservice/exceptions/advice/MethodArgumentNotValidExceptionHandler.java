package com.varundublish.omnirio.accountsservice.exceptions.advice;

import com.varundublish.omnirio.accountsservice.exceptions.CustomAccountErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomAccountErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        CustomAccountErrorResponse customAccountErrorResponse = new CustomAccountErrorResponse();
        customAccountErrorResponse.setStatus("400");
        if(fieldErrors.size()>0) {
            org.springframework.validation.FieldError fieldError = fieldErrors.get(0);
            customAccountErrorResponse.setMessage(fieldError.getDefaultMessage());
        }
        else {
            customAccountErrorResponse.setMessage("Bad Request");
        }
        return customAccountErrorResponse;
    }

}