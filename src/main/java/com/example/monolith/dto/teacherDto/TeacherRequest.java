package com.example.monolith.dto.teacherDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TeacherRequest {

    private String name;
    private String degree;


}
