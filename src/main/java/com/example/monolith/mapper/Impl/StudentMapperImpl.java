package com.example.monolith.mapper.Impl;

import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.entity.Student;
import com.example.monolith.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentResponse studentEntityToStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .age(student.getAge())
                .enrollments(student.getEnrollments())
                .name(student.getName())
                .build();
    }

    @Override
    public List<StudentResponse> AllEntityToAllResponse(List<Student> students) {
        List<StudentResponse> response = students.stream().map(this::studentEntityToStudentResponse).toList();
        return response;
    }

    @Override
    public Student studentRequestToStudentEntity(StudentRequest student) {
        return Student.builder()
                .age(student.getAge())
                .enrollments(student.getEnrollments())
                .name(student.getName()).build();
    }


}