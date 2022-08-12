package com.example.monolith.services;

import com.example.monolith.dto.ClientResponse;

import com.example.monolith.entity.Roles;
import com.example.monolith.utility.Exceptions.ObjectAlreadyExistException;
import com.example.monolith.utility.Exceptions.ObjectNotFoundException;

import java.util.List;

public interface ClientService<T> {

    ClientResponse deleteClient(Long id) throws ObjectNotFoundException;

    ClientResponse saveClient(T type) throws ObjectAlreadyExistException;

    ClientResponse getClient(Long id) throws ObjectNotFoundException;

    List<ClientResponse> getAllClients();

    String saveRole(Roles Role);
    void deleteRole(Long id);

    String AddRole(String userName, String roleName);

}
