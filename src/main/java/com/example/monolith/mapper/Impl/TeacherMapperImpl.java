package com.example.monolith.mapper.Impl;

import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.dto.teacherDto.TeacherResponse;
import com.example.monolith.entity.Teacher;
import com.example.monolith.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public TeacherResponse teacherEntityToTeacherResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .degree(teacher.getDegree())
                .name(teacher.getName())
                .build();
    }


    public List<TeacherResponse> AllEntityToAllResponse(List<Teacher> teachers) {
        List<TeacherResponse> responses = teachers.stream().map(this::teacherEntityToTeacherResponse).toList();
        return responses;
    }

    @Override
    public Teacher teacherRequestToTeacherEntity(TeacherRequest teacher) {
        return Teacher.builder()
                .name(teacher.getName())
                .degree(teacher.getDegree())
                .build();
    }


}
