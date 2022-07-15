package com.example.monolith.services;

import com.example.monolith.dto.courseDto.CourseRequest;
import com.example.monolith.dto.courseDto.CourseResponse;
import com.example.monolith.utility.exceptions.ObjectAlreadyExistException;
import com.example.monolith.utility.exceptions.ObjectNotFoundException;

import java.util.List;

public interface CourseService<T> {


    CourseResponse get(Long id) throws ObjectNotFoundException;

    CourseResponse save(T type) throws ObjectAlreadyExistException;

    List<CourseResponse> getAll();

    CourseResponse delete(Long id) throws ObjectNotFoundException;

    CourseResponse assignTeacher(Long courseId, Long teacherId) throws ObjectNotFoundException;


}
