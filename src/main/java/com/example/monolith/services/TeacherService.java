package com.example.monolith.services;

import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.dto.teacherDto.TeacherResponse;
import com.example.monolith.exceptions.EmptyDatabaseException;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import com.example.monolith.exceptions.ObjectNotFoundException;

import java.util.List;

public interface TeacherService {

    TeacherResponse get(Long id) throws ObjectNotFoundException;
    TeacherResponse save(TeacherRequest request) throws ObjectAlreadyExistException;
    TeacherResponse delete(Long id) throws ObjectNotFoundException;
    List<TeacherResponse> getAll() throws EmptyDatabaseException;
    TeacherResponse updateDegree(Long id,String degree) throws ObjectNotFoundException;




}
