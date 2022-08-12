package com.example.monolith.dto;

import com.example.monolith.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClientResponse {
    private Long id;
    private String name;
    private int age;
    private Set<Roles> roles;

}
