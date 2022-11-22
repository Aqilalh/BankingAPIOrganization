package com.BankingAPI.BankingAPIDEMO.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionHandler {
    @ControllerAdvice
    public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
        @ResponseBody
        protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

            CodeMessageError ef = new CodeMessageError(status.value(), ex.getMessage());
            return super.handleExceptionInternal(ex, ef, headers, status, request);
        }
    }
}

