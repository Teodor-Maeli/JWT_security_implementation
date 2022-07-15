package com.example.monolith.controllers;

import com.example.monolith.dto.courseDto.CourseResponse;
import com.example.monolith.dto.studentDto.StudentResponse;
import com.example.monolith.dto.teacherDto.TeacherResponse;
import com.example.monolith.utility.constants.Constants;
import com.example.monolith.utility.exceptions.EmptyDatabaseException;
import com.example.monolith.services.impl.CourseServiceImpl;
import com.example.monolith.services.impl.StudentServiceImpl;
import com.example.monolith.services.impl.TeacherServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@AllArgsConstructor
public class tablesController {

    StudentServiceImpl studentService;

    TeacherServiceImpl teacherService;

    CourseServiceImpl courseService;

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping("/show/students")
    public String studentsTable(Model model){
        List<StudentResponse> students = studentService.getAll();
        model.addAttribute("students",students);
        return "/students/students.html";

    }

    @PreAuthorize("hasRoles('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping("/show/teachers")
    public String teacherTable(Model model)  {
        List<TeacherResponse> teachers = null;
        try {
            teachers = teacherService.getAll();
        } catch (EmptyDatabaseException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.EMPTY);
        }
        model.addAttribute("teachers",teachers);
        return "/teachers/teachers.html";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/show/courses")
    public String coursesTable(Model model) {
        List<CourseResponse> courses = courseService.getAll();
        model.addAttribute("courses",courses);
        return "/courses/courses.html";
    }

}
