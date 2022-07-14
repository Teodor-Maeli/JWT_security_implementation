package com.example.monolith.services;

import com.example.monolith.dto.enrollmentDto.EnrollmentResponse;
import com.example.monolith.entity.Enrollment;
import com.example.monolith.exceptions.EmptyDatabaseException;
import com.example.monolith.exceptions.InvalidGradeException;
import com.example.monolith.exceptions.ObjectNotFoundException;
import com.example.monolith.exceptions.StudentNotAssignedException;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public interface EnrollmentService {

    List<EnrollmentResponse> getAllByStudent(Long studentId) throws StudentNotAssignedException, EmptyDatabaseException;

    List<EnrollmentResponse> getAll();

    EnrollmentResponse addGrade(Long cId, Long sId, double grade) throws InvalidGradeException, StudentNotAssignedException;

    EnrollmentResponse delete(Long cId, Long sId) throws StudentNotAssignedException;

    EnrollmentResponse enroll(Long sId, Long cId) throws ObjectNotFoundException;

    EnrollmentResponse getByCourseAndStudent(Long cId, Long sId) throws StudentNotAssignedException;

    double getStudentTotalAvg(Long id) throws EmptyDatabaseException, InvalidGradeException;

    double getCourseTotalAvg(Long id) throws EmptyDatabaseException, InvalidGradeException;

    List<EnrollmentResponse> showAllStudentsAndTeachers();

    TreeMap<String, TreeMap<String, Double>> showAllGroupedByCourseAndAvg();

    List<Enrollment> getAllFiltered();


}
