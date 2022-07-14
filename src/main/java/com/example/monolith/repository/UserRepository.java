package com.example.monolith.repository;


import com.example.monolith.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AdminEntity,Long> {

    Optional<AdminEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);


}
