package com.example.monolith.dto.userDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class AdminRequest {

    private String username;
    private String password;
}