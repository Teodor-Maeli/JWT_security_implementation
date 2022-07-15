package com.example.monolith.controllers;


import com.example.monolith.utility.constants.Constants;
import com.example.monolith.dto.enrollmentDto.EnrollmentResponse;
import com.example.monolith.utility.exceptions.EmptyDatabaseException;
import com.example.monolith.utility.exceptions.InvalidGradeException;
import com.example.monolith.utility.exceptions.ObjectNotFoundException;
import com.example.monolith.utility.exceptions.StudentNotAssignedException;
import com.example.monolith.services.impl.EnrollmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.TreeMap;

@RestController
@RequestMapping("/enrollments")
@AllArgsConstructor
public class EnrollmentController {


    EnrollmentServiceImpl enrollmentService;

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
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

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping
    public List<EnrollmentResponse> getAll() {
        return enrollmentService.getAll();
    }


    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @DeleteMapping(value = "/{cId}/{sId}")
    public EnrollmentResponse delete(@PathVariable Long cId, @PathVariable Long sId) {
        try {
            return enrollmentService.delete(cId, sId);
        } catch (StudentNotAssignedException e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.NOT_ASSIGNED);
        }

    }

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @PostMapping(value = "/{cId}/{sId}")
    public EnrollmentResponse enroll(@PathVariable Long sId, @PathVariable Long cId) {
        try {
            return enrollmentService.enroll(sId, cId);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NOT_EXIST);
        }
    }


    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping(value = "/{cId}/{sId}")
    public EnrollmentResponse getByCourseAndStudent(@PathVariable Long cId, @PathVariable Long sId) {
        try {
            return enrollmentService.getByCourseAndStudent(cId, sId);
        } catch (StudentNotAssignedException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.NON_ENROLL);
        }
    }


    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @PatchMapping(value = "/add/{cId}/{sId}/{grade}")
    public EnrollmentResponse addGrade(@PathVariable Long cId, @PathVariable Long sId, @PathVariable double grade) {
        try {
            return enrollmentService.addGrade(cId, sId, grade);
        } catch (InvalidGradeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constants.INVALID_GRADE);
        } catch (StudentNotAssignedException e) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, Constants.NOT_ASSIGNED);
        }

    }

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping(value = "/average/{sId}")
    public double getStudentTotalAvg(@PathVariable Long sId) {
        try {
            return enrollmentService.getStudentTotalAvg(sId);
        } catch (EmptyDatabaseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.EMPTY);
        } catch (InvalidGradeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constants.NO_GRADES);
        }
    }

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping(value = "/cavg/{cId}")
    public double getCourseAverage(@PathVariable Long cId) {
        try {
            return enrollmentService.getCourseTotalAvg(cId);
        } catch (EmptyDatabaseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.EMPTY);
        } catch (InvalidGradeException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, Constants.NO_GRADES);
        }
    }

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping(value = "/all")
    public List<EnrollmentResponse> showByCourseAndTeachers() {
        return enrollmentService.showAllStudentsAndTeachers();
    }

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping(value = "/sorted")
    public TreeMap<String, TreeMap<String, Double>> sorted() {
        return enrollmentService.showAllGroupedByCourseAndAvg();
    }

}
