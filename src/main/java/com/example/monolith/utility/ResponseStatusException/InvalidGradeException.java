package com.example.monolith.utility.ResponseStatusException;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidGradeException extends ResponseStatusException {

    public InvalidGradeException(String message){
        super(HttpStatus.EXPECTATION_FAILED,message);
    }
}
