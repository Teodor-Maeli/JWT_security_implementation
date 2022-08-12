package com.example.monolith.controllers;


import com.example.monolith.dto.ClientRequest;

import com.example.monolith.dto.ClientResponse;
import com.example.monolith.entity.Roles;
import com.example.monolith.services.impl.ClientServiceImpl;
import com.example.monolith.utility.TokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    ClientServiceImpl client;
    TokenGenerator tokenGenerator;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ClientResponse getClient(@PathVariable Long id) {
        return client.getClient(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<ClientResponse> getAll() {
        return client.getAllClients();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ClientResponse delete(@PathVariable Long id) {
        return client.deleteClient(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ClientResponse save(@RequestBody ClientRequest student) {
        return client.saveClient(student);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/roles/save")
    public String saveRole(@RequestBody Roles role) {
        return client.saveRole(role);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/roles/delete/{id}")
    public HttpStatus deleteRole(@PathVariable Long id) {
        client.deleteRole(id);
        return HttpStatus.ACCEPTED;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add/role")
    public String addRole(@RequestParam String username, @RequestParam String roleName) {
        return client.AddRole(username, roleName);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/refresh")
    public void refresh(HttpServletRequest request, HttpServletResponse response) {
        try {
            tokenGenerator.refreshToken(request,response);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
