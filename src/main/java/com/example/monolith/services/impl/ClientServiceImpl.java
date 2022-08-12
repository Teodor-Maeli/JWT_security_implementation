package com.example.monolith.services.impl;


import com.example.monolith.dto.ClientRequest;
import com.example.monolith.dto.ClientResponse;
import com.example.monolith.entity.Client;
import com.example.monolith.entity.Roles;
import com.example.monolith.mapper.Impl.ClientMapper;
import com.example.monolith.repository.ClientRepository;
import com.example.monolith.repository.RoleRepository;
import com.example.monolith.services.ClientService;
import com.example.monolith.utility.Exceptions.ObjectAlreadyExistException;
import com.example.monolith.utility.Exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.example.monolith.utility.enums.ExceptionMessage.EXIST;
import static com.example.monolith.utility.enums.ExceptionMessage.NOT_EXIST;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService<ClientRequest> {

    private final ClientRepository clientRepository;

    private final RoleRepository roleRepository;

    private final ClientMapper studentMapper;


    @Override
    public ClientResponse getClient(Long id) throws ObjectNotFoundException {
        if (clientRepository.existsById(id)) {
            Client client = clientRepository.findById(id).get();
            return studentMapper.entityToResponse(client);
        } else throw new ObjectNotFoundException(NOT_EXIST.getExceptionMessage());

    }

    @Override
    public List<ClientResponse> getAllClients() {
        List<Client> allClients = clientRepository.findAll();
        return studentMapper.AllEntityToAllResponse(allClients);
    }

    @Override
    public String saveRole(Roles role) {
        roleRepository.save(role);
        return role.getName();
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public String AddRole(String userName, String roleName) {
        Client client = clientRepository.findByUserName(userName);
        client.getRoles().add(roleRepository.findByName(roleName));
        return roleName;
    }

    @Override
    public ClientResponse deleteClient(Long id) throws ObjectNotFoundException {
        if (clientRepository.existsById(id)) {
            Client client = clientRepository.findById(id).get();
            clientRepository.delete(client);
            return studentMapper.entityToResponse(client);
        } else throw new ObjectNotFoundException(NOT_EXIST.getExceptionMessage());
    }

    @Override
    public ClientResponse saveClient(ClientRequest request) throws ObjectAlreadyExistException {
        Client client = studentMapper.requestToEntity(request);
        if (!clientRepository.existsByAgeAndName(request.getAge(), request.getName())) {
            clientRepository.save(client);
            return studentMapper.entityToResponse(client);
        } else throw new ObjectAlreadyExistException(EXIST.getExceptionMessage());
    }





}
