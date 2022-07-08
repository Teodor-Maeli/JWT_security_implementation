package com.example.monolith.controllers;


import com.example.monolith.constants.Constants;
import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.dto.teacherDto.TeacherResponse;
import com.example.monolith.exceptions.EmptyDatabaseException;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import com.example.monolith.exceptions.ObjectNotFoundException;
import com.example.monolith.services.impl.TeacherServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@AllArgsConstructor
public class TeacherController {

    TeacherServiceImpl teacherServiceImpl;

    @GetMapping(value = "/{id}")
    public TeacherResponse getTeachers(@PathVariable Long id) {
        try {
            return teacherServiceImpl.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }
    }

    @GetMapping
    public List<TeacherResponse> getAllTeachers() {
        try {
            return teacherServiceImpl.getAll();
        } catch (EmptyDatabaseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.EMPTY);
        }
    }

    @DeleteMapping(value = "/{id}")
    public TeacherResponse deleteTeacher(@PathVariable Long id){
        try {
            return teacherServiceImpl.delete(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }
    }

    @PostMapping
    public TeacherResponse save(@RequestBody TeacherRequest teacher){
        try {
            return teacherServiceImpl.save(teacher);
        } catch (ObjectAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.EXIST);
        }
    }

    @PatchMapping(value = "/{id}/{degree}")
    public TeacherResponse update(@PathVariable Long id,@PathVariable String degree){
        try {
            return teacherServiceImpl.updateDegree(id,degree);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }
    }





}