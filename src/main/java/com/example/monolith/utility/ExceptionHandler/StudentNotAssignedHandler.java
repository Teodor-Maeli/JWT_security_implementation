package com.example.monolith.utility.ExceptionHandler;

import com.example.monolith.utility.Exceptions.StudentNotAssignedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class StudentNotAssignedHandler {


        @ExceptionHandler(value={StudentNotAssignedException.class})
    public ResponseEntity<ResponsePayload> handleStudentNotAssigned(StudentNotAssignedException ex){
            ResponsePayload payload = new ResponsePayload();

            payload.setMessage(ex.getMessage());
            payload.setTimeStamp(LocalDateTime.now());
            payload.setStatus(HttpStatus.CONFLICT);

            return new ResponseEntity<>(payload,payload.getStatus());

        }

    }
