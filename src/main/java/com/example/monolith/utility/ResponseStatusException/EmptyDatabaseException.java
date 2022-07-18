package com.example.monolith.utility.ResponseStatusException;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmptyDatabaseException extends ResponseStatusException {

    public EmptyDatabaseException(String message){
        super(HttpStatus.NO_CONTENT,message);
    }

}
