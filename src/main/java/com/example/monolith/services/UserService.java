package com.example.monolith.services;

import com.example.monolith.utility.Exceptions.ObjectAlreadyExistException;

public interface UserService<T> {

    String createClientAccount(T type) throws ObjectAlreadyExistException;

}
