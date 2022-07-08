package com.example.monolith.repository;

import com.example.monolith.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    boolean existsByNameAndAndDegree(String name,String degree);
}
