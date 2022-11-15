package com.BankingAPI.BankingAPIDEMO.exceptions;

import org.springframework.http.HttpStatus;

public class CodeMessageError {

    private int code;
    private String message;

    public CodeMessageError(String message){
        this.code = HttpStatus.NOT_FOUND.value();
        this.message = message;
    }

    public CodeMessageError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
