package com.example.monolith.mapper;

import com.example.monolith.dto.courseDto.CourseRequest;
import com.example.monolith.dto.courseDto.CourseResponse;
import com.example.monolith.entity.Course;

import java.util.List;

public interface CourseMapper {

    public CourseResponse courseEntityToCourseResponse(Course course);


    public List<CourseResponse> allEntityToAllResponse(List<Course> courses);

    public Course courseRequestToCourseEntity(CourseRequest course);


}
