package com.example.monolith.services.impl;

import com.example.monolith.dto.enrollmentDto.EnrollmentResponse;
import com.example.monolith.entity.Enrollment;
import com.example.monolith.exceptions.EmptyDatabaseException;
import com.example.monolith.exceptions.InvalidGradeException;
import com.example.monolith.exceptions.ObjectNotFoundException;
import com.example.monolith.exceptions.StudentNotAssignedException;
import com.example.monolith.mapper.Impl.EnrollmentMapperImpl;
import com.example.monolith.repository.CourseRepository;
import com.example.monolith.repository.EnrollmentRepository;
import com.example.monolith.repository.StudentRepository;
import com.example.monolith.services.EnrollmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    CourseRepository courseRepository;
    StudentRepository studentRepository;
    EnrollmentRepository enrollmentRepository;
    EnrollmentMapperImpl enrollmentMapper;

    @Override
    public List<EnrollmentResponse> getAllByStudent(Long studentId) throws EmptyDatabaseException, StudentNotAssignedException {
        List<Enrollment> enrollment = enrollmentRepository.findAllByStudentId(studentId).orElseThrow(EmptyDatabaseException::new);
        if (!enrollment.isEmpty()) {
            return enrollmentMapper.AllEnrollmentsToAllResponse(enrollment);
        } else {
            throw new StudentNotAssignedException();
        }
    }

    @Override
    public List<EnrollmentResponse> getAll() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        return enrollmentMapper.AllEnrollmentsToAllResponse(enrollments);
    }

    @Override
    public EnrollmentResponse delete(Long cId, Long sId) throws StudentNotAssignedException {
        Enrollment enrollment = enrollmentRepository.findByCourseIdAndStudentId(cId, sId).orElseThrow(StudentNotAssignedException::new);
        enrollmentRepository.delete(enrollment);
        return enrollmentMapper.enrollmentEntityToEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentResponse addGrade(Long cId, Long sId, double grade) throws InvalidGradeException, StudentNotAssignedException {
        Enrollment enrollment = enrollmentRepository.findByCourseIdAndStudentId(cId, sId).orElseThrow(StudentNotAssignedException::new);
        if (grade >= 2 && grade <= 6) {
            enrollment.getGrades().add(grade);
        } else {
            throw new InvalidGradeException();
        }
        return enrollmentMapper.enrollmentEntityToEnrollmentResponse(enrollmentRepository.save(enrollment));

    }

    @Override
    public EnrollmentResponse enroll(Long sId, Long cId) throws ObjectNotFoundException {
        Enrollment enrollment;
        if (studentRepository.existsById(sId) && studentRepository.existsById(sId)) {
            enrollment = (Enrollment
                    .builder()
                    .course(courseRepository.findById(cId).get())
                    .student(studentRepository.findById(sId).get())
                    .build());
            return enrollmentMapper.enrollmentEntityToEnrollmentResponse(enrollmentRepository.save(enrollment));
        } else {
            throw new ObjectNotFoundException();
        }
    }


    @Override
    public EnrollmentResponse getByCourseAndStudent(Long cId, Long sId) throws StudentNotAssignedException {
        Enrollment enrollment = enrollmentRepository.findByCourseIdAndStudentId(cId, sId).orElseThrow(StudentNotAssignedException::new);
        return enrollmentMapper.enrollmentEntityToEnrollmentResponse(enrollment);

    }

    @Override
    public double getStudentTotalAvg(Long id) throws EmptyDatabaseException, InvalidGradeException {
        List<Enrollment> enrollment = enrollmentRepository.findAllByStudentId(id).orElseThrow(EmptyDatabaseException::new);
        return enrollment
                .stream()
                .filter(s -> !s.getGrades().isEmpty())
                .mapToDouble(enroll -> enroll.getGrades()
                        .stream()
                        .mapToDouble(num -> num).average()
                        .orElse(0.0))
                .average()
                .orElseThrow(InvalidGradeException::new);

    }

    @Override
    public double getCourseTotalAvg(Long id) throws EmptyDatabaseException, InvalidGradeException {
        List<Enrollment> enrollment = enrollmentRepository.findAllByCourseId(id).orElseThrow(EmptyDatabaseException::new);
        return enrollment
                .stream()
                .filter(s -> !s.getGrades().isEmpty())
                .mapToDouble(enroll -> enroll.getGrades()
                        .stream()
                        .mapToDouble(num -> num).average()
                        .orElse(0.0))
                .average()
                .orElseThrow(InvalidGradeException::new);

    }

    @Override
    public List<EnrollmentResponse> showAllStudentsAndTeachers() {
        List<Enrollment> enrollment = enrollmentRepository.findAll().stream().filter(enroll -> enroll.getCourse() != null).collect(Collectors.toList());

        List<EnrollmentResponse> responses = enrollmentMapper.AllEnrollmentsToAllResponse(enrollment);
        return responses
                .stream()
                .filter(response -> response.getCourse().getTeacher() != null)
                .collect(Collectors.toList());
    }

    @Override
    public TreeMap<String, TreeMap<String, Double>> showAllGroupedByCourseAndAvg() {
        List<Enrollment> enrollments = getAllFiltered();

        TreeMap<String, TreeMap<String, Double>> grouped = new TreeMap<>();

        while (!enrollments.isEmpty()) {
            double averages = enrollments.stream()
                    .filter(s -> !s.getGrades().isEmpty() && s.getCourse().getName().equals(enrollments.get(0).getCourse().getName()))
                    .mapToDouble(enroll -> enroll.getGrades()
                            .stream()
                            .mapToDouble(num -> num).average()
                            .orElse(0.0))
                    .average()
                    .orElse(0.0);

                grouped.putIfAbsent(enrollments.get(0).getCourse().getName(), new TreeMap<>());
                grouped.get(enrollments.get(0).getCourse().getName()).put(enrollments.get(0).getStudent().getName(),averages);

            enrollments.remove(0);
        }
        return grouped;
    }


    @Override
    public List<Enrollment> getAllFiltered(){
        return enrollmentRepository.findAll()
                .stream()
                .filter(e -> e.getCourse() != null && !e.getGrades().isEmpty() && e.getStudent() != null)
                .collect(Collectors.toList());
    }


}