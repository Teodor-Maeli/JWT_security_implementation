package com.example.monolith.utility.ResponseStatusException;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ObjectNotFoundException extends ResponseStatusException {

    public ObjectNotFoundException(String message){
        super(HttpStatus.CONFLICT,message);
    }

}
