package com.example.monolith.mapper;

import com.example.monolith.dto.enrollmentDto.EnrollmentResponse;
import com.example.monolith.entity.Enrollment;

import java.util.List;

public interface EnrollmentMapper {

    public EnrollmentResponse enrollmentEntityToEnrollmentResponse(Enrollment enrollment);

    public List<EnrollmentResponse> AllEnrollmentsToAllResponse(List<Enrollment> enrollments);



}
