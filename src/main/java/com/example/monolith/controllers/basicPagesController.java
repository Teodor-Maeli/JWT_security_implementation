package com.example.monolith.controllers;

import com.example.monolith.authentication.UserService;
import com.example.monolith.authentication.userDto.AdminDto;
import com.example.monolith.constants.Constants;
import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller
@AllArgsConstructor
public class basicPagesController {

    UserService userService;

    @RequestMapping(value = "/login")
    public String login(){
        return "login.html";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value ="/index")
    public String index(){
        return "index.html";
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/register/students")
    public String signupStudent(@ModelAttribute StudentRequest createStudentAccount) {
        if(createStudentAccount.getAge()!=null && createStudentAccount.getPassword()!=null){
            try {
                userService.createStudentAccount(createStudentAccount);
            } catch (ObjectAlreadyExistException e) {
                throw new ResponseStatusException(HttpStatus.FOUND, createStudentAccount.getUsername()+ " "+Constants.ALREADY_REGISTERED);
            }
        }
        return "studentsReg.html";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/register/teachers")
    public String signupTeacher(@ModelAttribute TeacherRequest createTeacherAccount) {
        if(createTeacherAccount.getPassword()!=null && createTeacherAccount.getAge()!=null){
            try {
                userService.createTeacherAccount(createTeacherAccount);
            } catch (ObjectAlreadyExistException e) {
                throw new ResponseStatusException(HttpStatus.FOUND, createTeacherAccount.getUsername()+ " "+Constants.ALREADY_REGISTERED);
            }
        }
        return "/teachersReg.html";
    }


}
