package com.example.monolith.services;

import com.example.monolith.utility.Exceptions.ObjectAlreadyExistException;

public interface UserService<T,T1,T2> {
    String createUser(T type) throws ObjectAlreadyExistException;
    String createStudentAccount(T1 type) throws ObjectAlreadyExistException;
    String createTeacherAccount(T2 type) throws ObjectAlreadyExistException;
}
