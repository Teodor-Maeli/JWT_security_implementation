package com.example.monolith.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int age;
    private String userName;
    private String password;
    private boolean active;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;



}
