package com.example.monolith.repository;

import com.example.monolith.entity.Course;
import com.example.monolith.entity.Enrollment;
import com.example.monolith.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<List<Enrollment>> findAllByStudentId(Long id);

    Optional<List<Enrollment>> findAllByCourseId(Long id);

    Optional<Enrollment> findByCourseIdAndStudentId(Long courseId, Long studentId);


}
