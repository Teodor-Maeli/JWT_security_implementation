package com.example.monolith.mapper.Impl;

import com.example.monolith.dto.ClientRequest;

import com.example.monolith.dto.ClientResponse;
import com.example.monolith.entity.Client;
import com.example.monolith.entity.Roles;
import com.example.monolith.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.monolith.utility.enums.Authorities.ADMIN;

@Component
@AllArgsConstructor
public class ClientMapper extends BaseMapper implements com.example.monolith.mapper.ClientMapper<Client, ClientRequest>, CommandLineRunner {


    private ClientRepository clientRepository;


    @Override
    public ClientResponse entityToResponse(Client student) {
        return ClientResponse.builder()
                .id(student.getId())
                .age(student.getAge())
                .name(student.getName())
                .roles(student.getRoles())
                .build();
    }

    @Override
    public List<ClientResponse> AllEntityToAllResponse(List<Client> students) {
        List<ClientResponse> response = students.stream().map(this::entityToResponse).toList();
        return response;
    }

    @Override
    public Client
    requestToEntity(ClientRequest student) {
        return Client.builder()
                .age(student.getAge())
                .name(student.getName())
                .userName(student.getUsername())
                .password(passwordEncoder().encode(student.getPassword()))
                .active(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    }


    @Override
    public void run(String... args) throws Exception {
        if (clientRepository.findAll().isEmpty()) {
            Set<Roles> roles = new HashSet<>();
            roles.add(Roles.builder()
                    .name(ADMIN.name())
                    .build());


            clientRepository.save(Client.builder()
                    .name("admin")
                    .age(999)
                    .userName("admin")
                    .password(passwordEncoder().encode("admin"))
                    .active(true)
                    .roles(roles)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build());
        }


    }
}
