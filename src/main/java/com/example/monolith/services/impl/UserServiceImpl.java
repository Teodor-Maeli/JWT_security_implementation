package com.example.monolith.services.impl;

import com.example.monolith.dto.userDto.AdminRequest;
import com.example.monolith.services.UserService;
import com.example.monolith.utility.constants.Constants;
import com.example.monolith.dto.studentDto.StudentRequest;
import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.entity.AdminEntity;
import com.example.monolith.entity.Student;
import com.example.monolith.entity.Teacher;
import com.example.monolith.utility.exceptions.ObjectAlreadyExistException;
import com.example.monolith.mapper.Impl.AdminMapper;
import com.example.monolith.mapper.Impl.StudentMapperImpl;
import com.example.monolith.mapper.Impl.TeacherMapperImpl;
import com.example.monolith.repository.StudentRepository;
import com.example.monolith.repository.TeacherRepository;
import com.example.monolith.repository.UserRepository;
import com.example.monolith.securityConfig.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService<AdminRequest,StudentRequest,TeacherRequest> {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    private final StudentMapperImpl studentMapper;

    private final TeacherMapperImpl teacherMapper;
    private final AdminMapper userMapper;


    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {

        if (studentRepository.existsByUserName(username)) {
            Optional<Student> user = Optional.of(studentRepository.findByUserName(username).get());
            return user.map(AuthUser::new).get();
        } else if (teacherRepository.existsByUserName(username)) {
            Optional<Teacher> user = Optional.of(teacherRepository.findByUserName(username).get());
            return user.map(AuthUser::new).get();
        } else if (userRepository.existsByUserName(username)) {
            Optional<AdminEntity> user = Optional.of(userRepository.findByUserName(username).get());
            return user.map(AuthUser::new).get();
        } else throw new UsernameNotFoundException(Constants.NOT_REGISTERED);
    }


    public String createUser(AdminRequest createUserRequest) throws ObjectAlreadyExistException {
        AdminEntity user = userMapper.createRequestToEntity(createUserRequest);
        if (!userRepository.existsByUserName(user.getUserName())) {
            userRepository.save(user);
            return user.getUserName();
        } else {
            throw new ObjectAlreadyExistException();
        }
    }

    public String createStudentAccount(StudentRequest student) throws ObjectAlreadyExistException {
        Student user = studentMapper.studentRequestToStudentEntity(student);
        if (!studentRepository.existsByUserName(user.getUserName()) && !studentRepository.existsByName(user.getName())) {
            studentRepository.save(user);
            return user.getUserName();
        } else {
            throw new ObjectAlreadyExistException();
        }

    }


    public String createTeacherAccount(TeacherRequest teacher) throws ObjectAlreadyExistException {
        Teacher user = teacherMapper.teacherRequestToTeacherEntity(teacher);
        if (!teacherRepository.existsByUserName(user.getUserName()) && !teacherRepository.existsByName(user.getName())) {
            teacherRepository.save(user);
            return user.getUserName();
        } else {
            throw new ObjectAlreadyExistException();
        }

    }


}
