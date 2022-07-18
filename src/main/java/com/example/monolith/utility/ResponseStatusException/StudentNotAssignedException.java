package com.example.monolith.utility.ResponseStatusException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StudentNotAssignedException extends ResponseStatusException {
    public StudentNotAssignedException(String message){
        super(HttpStatus.EXPECTATION_FAILED,message);
    }
}
