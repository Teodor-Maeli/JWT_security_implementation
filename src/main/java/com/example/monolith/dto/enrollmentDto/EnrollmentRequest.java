package com.example.monolith.dto.enrollmentDto;

import com.example.monolith.entity.Course;
import com.example.monolith.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EnrollmentRequest {

    private Student students;
    private Course course;
    private List<Double> grades;

}
