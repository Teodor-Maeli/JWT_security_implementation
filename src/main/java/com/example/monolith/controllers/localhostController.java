package com.example.monolith.controllers;

import com.example.monolith.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("")
public class localhostController {

    @GetMapping
    public String message(){
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, Constants.NO_RESOURCES);
    }

}
