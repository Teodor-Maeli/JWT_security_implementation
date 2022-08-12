package com.example.monolith.key.repository;

import com.example.monolith.key.model.SecretKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<SecretKey,Long> {
    @Override
    Optional<SecretKey> findById(Long aLong);
}
