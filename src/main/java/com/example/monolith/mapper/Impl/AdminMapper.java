package com.example.monolith.mapper.Impl;

import com.example.monolith.entity.AdminEntity;
import com.example.monolith.dto.userDto.AdminRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.monolith.utility.enums.Roles.ADMIN;

@AllArgsConstructor
@Component
public class AdminMapper extends BaseMapper {


    public AdminEntity createRequestToEntity(AdminRequest admin) {
        return AdminEntity.builder()
                .password(passwordEncoder().encode(admin.getPassword()))
                .userName(admin.getUsername())
                .role(ADMIN.name())
                .active(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isAccountNonExpired(true)
                .build();
    }


}
