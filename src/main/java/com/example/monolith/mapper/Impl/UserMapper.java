package com.example.monolith.mapper.Impl;

import com.example.monolith.entity.AdminEntity;
import com.example.monolith.authentication.userDto.AdminDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper extends BaseMapper {


    public AdminEntity createRequestToEntity(AdminDto createUserRequest) {
        return AdminEntity.builder()
                .password(passwordEncoder().encode(createUserRequest.getPassword()))
                .userName(createUserRequest.getUsername())
                .active(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .build();
    }


}
