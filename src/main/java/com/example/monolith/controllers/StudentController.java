package com.example.monolith.controllers;

import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.services.impl.StudentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    StudentServiceImpl studentService;

    @SneakyThrows
    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")
    @GetMapping(value = "/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        return studentService.get(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public StudentResponse delete(@PathVariable Long id) {
        return studentService.delete(id);
    }


    @PreAuthorize("hasAnyRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @PostMapping
    public StudentResponse save(@RequestBody StudentRequest student) {
        return studentService.save(student);
    }

}
