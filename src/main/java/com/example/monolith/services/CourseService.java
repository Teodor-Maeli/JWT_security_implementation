package com.example.monolith.services;

import com.example.monolith.dto.courseDto.CourseRequest;
import com.example.monolith.dto.courseDto.CourseResponse;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import com.example.monolith.exceptions.ObjectNotFoundException;

import java.util.List;

public interface CourseService {


    CourseResponse get(Long id) throws ObjectNotFoundException;

    CourseResponse save(CourseRequest request) throws ObjectAlreadyExistException;

    List<CourseResponse> getAll();

    CourseResponse delete(Long id) throws ObjectNotFoundException;

    CourseResponse assignTeacher(Long courseId,Long teacherId) throws ObjectNotFoundException;




}
