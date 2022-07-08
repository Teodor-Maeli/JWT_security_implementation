package com.example.monolith.repository;

import com.example.monolith.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    boolean existsByAgeAndName(int age, String name);
}
