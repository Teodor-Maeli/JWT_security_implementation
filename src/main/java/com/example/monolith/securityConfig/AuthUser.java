package com.example.monolith.securityConfig;

import com.example.monolith.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Data
public class AuthUser implements UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;
    private Set<GrantedAuthority> authorities;

    public AuthUser(Client user) {

        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.isAccountNonExpired = user.isAccountNonExpired();
        this.isAccountNonLocked = user.isAccountNonLocked();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.isEnabled = user.isEnabled();
        this.authorities = user.getRoles().stream()
                .map(role->new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return userName;
    }


}
