package com.example.monolith.services.impl;

import com.example.monolith.dto.courseDto.CourseRequest;
import com.example.monolith.dto.courseDto.CourseResponse;
import com.example.monolith.entity.Course;
import com.example.monolith.entity.Teacher;
import com.example.monolith.utility.exceptions.ObjectAlreadyExistException;
import com.example.monolith.utility.exceptions.ObjectNotFoundException;
import com.example.monolith.mapper.Impl.CourseMapperImpl;
import com.example.monolith.repository.CourseRepository;
import com.example.monolith.repository.TeacherRepository;
import com.example.monolith.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    CourseRepository courseRepository;

    TeacherRepository teacherRepository;
    CourseMapperImpl courseMapper;


    @Override
    public CourseResponse get(Long courseId) throws ObjectNotFoundException {
        if (courseRepository.existsById(courseId)) {
            Course response = courseRepository.findById(courseId).get();
            return courseMapper.courseEntityToCourseResponse(response);
        } else {
            throw new ObjectNotFoundException();
        }

    }

    @Override
    public List<CourseResponse> getAll() {
        List<Course> responseList = courseRepository.findAll();
        return courseMapper.allEntityToAllResponse(responseList);
    }

    @Override
    public CourseResponse delete(Long id) throws ObjectNotFoundException {
        if (courseRepository.existsById(id)) {
            Course course = courseRepository.findById(id).get();
            courseRepository.delete(course);
            return courseMapper.courseEntityToCourseResponse(course);
        } else {
            throw new ObjectNotFoundException();
        }

    }


    @Override
    public CourseResponse save(CourseRequest request) throws ObjectAlreadyExistException {
        Course course = courseMapper.courseRequestToCourseEntity(request);
        if (!courseRepository.existsByName(request.getName())) {
            courseRepository.save(course);
            return courseMapper.courseEntityToCourseResponse(course);
        } else {
            throw new ObjectAlreadyExistException();
        }
    }

    @Override
    public CourseResponse assignTeacher(Long courseId, Long teacherId) throws ObjectNotFoundException {
        Course course = courseRepository.findById(courseId).orElseThrow(ObjectNotFoundException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(ObjectNotFoundException::new);
        course.setTeacher(teacher);
        courseRepository.save(course);
        return courseMapper.courseEntityToCourseResponse(course);
    }


}
