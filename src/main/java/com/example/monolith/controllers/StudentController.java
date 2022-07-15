package com.example.monolith.controllers;

import com.example.monolith.constants.Constants;
import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import com.example.monolith.exceptions.ObjectNotFoundException;
import com.example.monolith.services.impl.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    StudentServiceImpl studentService;

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_STUDENT')")
    @GetMapping(value = "/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        try {
            return studentService.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value = "/{id}")
    public StudentResponse delete(@PathVariable Long id) {
        try {
            return studentService.delete(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public StudentResponse save(@RequestBody StudentRequest student) {
        try {
            return studentService.save(student);
        } catch (ObjectAlreadyExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, Constants.EXIST);
        }
    }

}
