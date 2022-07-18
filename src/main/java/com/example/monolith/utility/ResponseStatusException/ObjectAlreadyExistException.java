package com.example.monolith.utility.ResponseStatusException;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ObjectAlreadyExistException extends ResponseStatusException {

    public ObjectAlreadyExistException(String message){
        super(HttpStatus.BAD_REQUEST,message);
    }

}
