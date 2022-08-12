package com.example.monolith.services.impl;

import com.example.monolith.entity.Client;
import com.example.monolith.services.UserService;
import com.example.monolith.dto.ClientRequest;
import com.example.monolith.utility.Exceptions.ObjectAlreadyExistException;
import com.example.monolith.mapper.Impl.ClientMapper;
import com.example.monolith.repository.ClientRepository;
import com.example.monolith.securityConfig.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.monolith.utility.enums.ExceptionMessage.ALREADY_REGISTERED;
import static com.example.monolith.utility.enums.ExceptionMessage.NOT_REGISTERED;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService<ClientRequest> {

    private final ClientRepository clientRepository;
    private final ClientMapper studentMapper;





    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        if (clientRepository.existsByUserName(username)) {
            Client user = clientRepository.findByUserName(username);
            return new AuthUser(user);
        } else throw new UsernameNotFoundException(NOT_REGISTERED.getExceptionMessage());
    }



    public String createClientAccount(ClientRequest student) throws ObjectAlreadyExistException {
        Client user = studentMapper.requestToEntity(student);
        if (!clientRepository.existsByUserName(user.getUserName()) && !clientRepository.existsByName(user.getName())) {
            clientRepository.save(user);
            return user.getUserName();
        } else throw new ObjectAlreadyExistException(ALREADY_REGISTERED.getExceptionMessage());

    }






}
