package com.example.monolith.authentication.userDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Data
@Component
public class AdminDto {

    private String username;
    private String password;
}
