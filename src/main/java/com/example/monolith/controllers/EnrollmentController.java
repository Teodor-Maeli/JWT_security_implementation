package com.example.monolith.controllers;


import com.example.monolith.constants.Constants;
import com.example.monolith.dto.enrollmentDto.EnrollmentResponse;
import com.example.monolith.exceptions.EmptyDatabaseException;
import com.example.monolith.exceptions.InvalidGradeException;
import com.example.monolith.exceptions.ObjectNotFoundException;
import com.example.monolith.exceptions.StudentNotAssignedException;
import com.example.monolith.services.impl.EnrollmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/v1/enrollments")
@AllArgsConstructor
public class EnrollmentController {


    EnrollmentServiceImpl enrollmentService;

    @GetMapping(value = "/{id}")
    public List<EnrollmentResponse> getEnrollment(@PathVariable Long id) {
        try {
            return enrollmentService.getAllByStudent(id);
        } catch (StudentNotAssignedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.NOT_ASSIGNED);
        } catch (EmptyDatabaseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.EMPTY);
        }
    }

    @GetMapping
    public List<EnrollmentResponse> getAll() {
        return enrollmentService.getAll();
    }


    @DeleteMapping(value = "/{cId}/{sId}")
    public EnrollmentResponse delete(@PathVariable Long cId, @PathVariable Long sId) {
        try {
            return enrollmentService.delete(cId, sId);
        } catch (StudentNotAssignedException e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.NOT_ASSIGNED);
        }

    }

    @PostMapping(value = "/{cId}/{sId}")
    public EnrollmentResponse enroll(@PathVariable Long sId, @PathVariable Long cId) {
        try {
            return enrollmentService.enroll(sId, cId);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }
    }


    @GetMapping(value = "/{cId}/{sId}")
    public EnrollmentResponse getByCourseAndStudent(@PathVariable Long cId, @PathVariable Long sId) {
        try {
            return enrollmentService.getByCourseAndStudent(cId, sId);
        } catch (StudentNotAssignedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NON_ENROLL);
        }
    }


    @PatchMapping(value = "/{cId}/{sId}/add/{grade}")
    public EnrollmentResponse addGrade(@PathVariable Long cId, @PathVariable Long sId, @PathVariable double grade) {
        try {
            return enrollmentService.addGrade(cId, sId, grade);
        } catch (InvalidGradeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constants.INVALID_GRADE);
        } catch (StudentNotAssignedException e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.NOT_ASSIGNED);
        }

    }

    @GetMapping(value = "/average/{sId}")
    public double getSpecificAvg(@PathVariable Long sId) {
        try {
            return enrollmentService.getStudentTotalAvg(sId);
        } catch (EmptyDatabaseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.EMPTY);
        } catch (InvalidGradeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,Constants.NO_GRADES);
        }
    }

    @GetMapping(value ="/cavg/{cId}" )
    public double getCourseAverage(@PathVariable Long cId){
        try {
            return enrollmentService.getCourseTotalAvg(cId);
        } catch (EmptyDatabaseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.EMPTY);
        } catch (InvalidGradeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,Constants.NO_GRADES);
        }
    }

    @GetMapping(value = "/all")
    public List<EnrollmentResponse> showByCourseAndTeachers(){
        return enrollmentService.showAllStudentsAndTeachers();
    }

    @GetMapping(value = "/sorted")
    public TreeMap<String, TreeMap<String, Double>> sorted(){
        return enrollmentService.showAllGroupedByCourseAndAvg();
    }

}
