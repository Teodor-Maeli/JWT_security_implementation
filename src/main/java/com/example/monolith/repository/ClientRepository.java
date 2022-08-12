package com.example.monolith.repository;

import com.example.monolith.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    boolean existsByAgeAndName(int age, String name);
    boolean existsByUserName(String username);

    boolean existsByName(String name);
   Client findByUserName(String userName);
}
