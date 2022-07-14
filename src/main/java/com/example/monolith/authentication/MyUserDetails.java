package com.example.monolith.authentication;

import com.example.monolith.entity.AdminEntity;
import com.example.monolith.entity.Student;
import com.example.monolith.entity.Teacher;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class MyUserDetails implements UserDetails {

    private String userName;
    private String password;
    private boolean active;
    private String roles;
    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(AdminEntity user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();

        this.isAccountNonExpired = user.isAccountNonExpired();
        this.isAccountNonLocked = user.isAccountNonLocked();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.isEnabled = user.isEnabled();
    }
    public MyUserDetails(Student user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();

        this.isAccountNonExpired = user.isAccountNonExpired();
        this.isAccountNonLocked = user.isAccountNonLocked();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.isEnabled = user.isEnabled();
    }

    public MyUserDetails(Teacher user) {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();

        this.isAccountNonExpired = user.isAccountNonExpired();
        this.isAccountNonLocked = user.isAccountNonLocked();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.isEnabled = user.isEnabled();
    }

    @Override
    public String getUsername() {
        return userName;
    }


}
