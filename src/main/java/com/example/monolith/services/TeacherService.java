package com.example.monolith.services;

import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.dto.teacherDto.TeacherResponse;
import com.example.monolith.utility.exceptions.EmptyDatabaseException;
import com.example.monolith.utility.exceptions.ObjectAlreadyExistException;
import com.example.monolith.utility.exceptions.ObjectNotFoundException;

import java.util.List;

public interface TeacherService<T> {

    TeacherResponse get(Long id) throws ObjectNotFoundException;
    TeacherResponse save(T type) throws ObjectAlreadyExistException;
    TeacherResponse delete(Long id) throws ObjectNotFoundException;
    List<TeacherResponse> getAll() throws EmptyDatabaseException;
    TeacherResponse updateDegree(Long id,String degree) throws ObjectNotFoundException;




}
