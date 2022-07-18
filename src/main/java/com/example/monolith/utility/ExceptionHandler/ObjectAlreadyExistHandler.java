package com.example.monolith.utility.ExceptionHandler;

import com.example.monolith.utility.Exceptions.ObjectAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ObjectAlreadyExistHandler {


    @ExceptionHandler(value = {ObjectAlreadyExistException.class})
    public ResponseEntity<ResponsePayload> handleStudentNotAssigned(ObjectAlreadyExistException ex) {
        ResponsePayload payload = new ResponsePayload();

        payload.setMessage(ex.getMessage());
        payload.setTimeStamp(LocalDateTime.now());
        payload.setStatus(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(payload, payload.getStatus());

    }
}