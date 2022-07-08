package com.example.monolith.mapper;

import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.dto.teacherDto.TeacherResponse;
import com.example.monolith.entity.Teacher;

public interface TeacherMapper {

    public TeacherResponse teacherEntityToTeacherResponse(Teacher teacher);

    public Teacher teacherRequestToTeacherEntity(TeacherRequest teacher);

}
