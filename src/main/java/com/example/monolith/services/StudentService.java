package com.example.monolith.services;

import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import com.example.monolith.exceptions.ObjectNotFoundException;

import java.util.List;

public interface StudentService {

    StudentResponse delete(Long id) throws ObjectNotFoundException;

    StudentResponse save(StudentRequest request) throws ObjectAlreadyExistException;

    StudentResponse get(Long id) throws ObjectNotFoundException;

    List<StudentResponse> getAll();

}
