package com.example.monolith.controllers;


import com.example.monolith.constants.Constants;
import com.example.monolith.dto.courseDto.CourseRequest;
import com.example.monolith.dto.courseDto.CourseResponse;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import com.example.monolith.exceptions.ObjectNotFoundException;
import com.example.monolith.services.impl.CourseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {

    CourseServiceImpl courseService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/{id}")
    public CourseResponse getCourse(@PathVariable Long id) {
        try {
            return courseService.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<CourseResponse> getAll(){
        return courseService.getAll();
    }



    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{id}")
    public CourseResponse delete(@PathVariable Long id){
        try {
           return courseService.delete(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,Constants.NOT_EXIST);
        }
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public CourseResponse save(@RequestBody CourseRequest course) {
        try {
            return courseService.save(course);
        } catch (ObjectAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,Constants.EXIST);
        }
    }



    @PatchMapping(value = "/{cId}/{tId}")
    public CourseResponse assignTeacher(@PathVariable Long cId,@PathVariable Long tId){
        try {
            return courseService.assignTeacher(cId,tId);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,Constants.EXIST);
        }
    }


}
