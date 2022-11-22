package com.BankingAPI.BankingAPIDEMO.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus code, Object responseObj) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code.value());
        map.put("message", message);
        map.put("data", responseObj);



        return new ResponseEntity<Object>(map, code);
    }

    public static ResponseEntity<Object> generateResponseNoObj(String message, HttpStatus code) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code.value());
        map.put("message", message);
        return new ResponseEntity<Object>(map, code);
    }

}

