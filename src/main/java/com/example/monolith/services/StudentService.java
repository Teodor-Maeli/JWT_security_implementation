package com.example.monolith.services;

import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.utility.ResponseStatusException.ObjectAlreadyExistException;
import com.example.monolith.utility.ResponseStatusException.ObjectNotFoundException;

import java.util.List;

public interface StudentService<T> {

    StudentResponse delete(Long id) throws ObjectNotFoundException;

    StudentResponse save(T type) throws ObjectAlreadyExistException;

    StudentResponse get(Long id) throws ObjectNotFoundException;

    List<StudentResponse> getAll();

}
