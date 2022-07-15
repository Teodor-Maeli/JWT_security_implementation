package com.example.monolith.services.impl;


import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.entity.Student;
import com.example.monolith.utility.exceptions.ObjectAlreadyExistException;
import com.example.monolith.utility.exceptions.ObjectNotFoundException;
import com.example.monolith.mapper.Impl.StudentMapperImpl;
import com.example.monolith.repository.CourseRepository;
import com.example.monolith.repository.StudentRepository;
import com.example.monolith.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Component
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    CourseRepository courseRepository;
    StudentMapperImpl studentMapper;


    @Override
    public StudentResponse get(Long id) throws ObjectNotFoundException {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            return studentMapper.studentEntityToStudentResponse(student);
        } else {
            throw new ObjectNotFoundException();
        }

    }

    @Override
    public List<StudentResponse> getAll() {
        List<Student> allStudent = studentRepository.findAll();
        return studentMapper.AllEntityToAllResponse(allStudent);
    }

    @Override
    public StudentResponse delete(Long id) throws ObjectNotFoundException {
        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            studentRepository.delete(student);
            return studentMapper.studentEntityToStudentResponse(student);
        } else {
            throw new ObjectNotFoundException();
        }
    }

    @Override
    public StudentResponse save(StudentRequest request) throws ObjectAlreadyExistException {
        Student student = studentMapper.studentRequestToStudentEntity(request);
        if (!studentRepository.existsByAgeAndName(request.getAge(), request.getName())) {
            studentRepository.save(student);
            return studentMapper.studentEntityToStudentResponse(student);
        } else {
            throw new ObjectAlreadyExistException();
        }
    }

}
