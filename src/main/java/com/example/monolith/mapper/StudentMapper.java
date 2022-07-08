package com.example.monolith.mapper;

import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.entity.Student;

import java.util.List;

public interface StudentMapper {

    public StudentResponse studentEntityToStudentResponse(Student student);

    public List<StudentResponse> AllEntityToAllResponse(List<Student> students);

    public Student studentRequestToStudentEntity(StudentRequest student);

}
