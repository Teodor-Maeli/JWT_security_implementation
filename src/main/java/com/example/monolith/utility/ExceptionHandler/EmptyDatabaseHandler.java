package com.example.monolith.utility.ExceptionHandler;

import com.example.monolith.utility.Exceptions.EmptyDatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmptyDatabaseHandler {


    @ExceptionHandler(value = {EmptyDatabaseException.class})
    public ResponseEntity<ResponsePayload> handleEmptyDatabase(EmptyDatabaseException ex) {
        ResponsePayload payload = new ResponsePayload();

        payload.setMessage(ex.getMessage());
        payload.setTimeStamp(LocalDateTime.now());
        payload.setStatus(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(payload, payload.getStatus());

    }
}