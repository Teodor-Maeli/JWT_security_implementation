package com.example.monolith.services.impl;

import com.example.monolith.dto.teacherDto.TeacherRequest;
import com.example.monolith.dto.teacherDto.TeacherResponse;
import com.example.monolith.entity.Teacher;
import com.example.monolith.exceptions.EmptyDatabaseException;
import com.example.monolith.exceptions.ObjectAlreadyExistException;
import com.example.monolith.exceptions.ObjectNotFoundException;
import com.example.monolith.mapper.Impl.TeacherMapperImpl;
import com.example.monolith.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TeacherServiceImplTest {

    TeacherRepository teacherRepository;
    TeacherServiceImpl teacherService;
    TeacherMapperImpl teacherMapper = new TeacherMapperImpl();
    TeacherRequest request;
    Teacher teacher;

    @BeforeEach
    void setup() {
        teacherRepository = mock(TeacherRepository.class);
        teacherService = new TeacherServiceImpl(teacherRepository, teacherMapper);

        request = TeacherRequest.builder()
                .name("Christian")
                .degree("Professor")
                .build();

        teacher = Teacher.builder()
                .degree("Professor")
                .name("Christian")
                .id(1L)
                .build();
    }


    @Test
    void get() {
        when(teacherRepository.existsById(anyLong())).thenReturn(true);
        when(teacherRepository.findById(1L)).thenReturn(Optional.ofNullable(teacher));
        assertAll(
                () -> assertEquals(teacherService.get(1L).getName(), teacher.getName()),
                () -> assertTrue(teacherService.get(1L).getName().equals(teacher.getName())),
                () -> assertNotNull(teacherService.get(1L)));
    }

    @Test
    void getAll() {
        when(teacherRepository.findAll()).thenReturn(Arrays.asList(teacher));
        assertAll(
                () -> assertNotNull(teacherService.getAll()),
                () -> assertEquals(teacherService.getAll().size(), 1),
                () -> assertSame(teacherService.getAll().get(0).getId(), 1L));

    }

    @Test
    void getAllThrows() {
        when(teacherRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(EmptyDatabaseException.class, () -> teacherService.getAll());
    }

    @Test
    void deleteThrows() {
        when(teacherRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(ObjectNotFoundException.class, () -> teacherService.delete(anyLong()));
    }

    @Test
    void save() {
        when(teacherRepository.existsByNameAndAndDegree(anyString(), anyString())).thenReturn(true);
        assertAll(() -> assertEquals(teacherService.save(request).getName(), teacher.getName()),
                () -> assertEquals(teacherService.save(request).getDegree(), "Professor"),
                () -> assertNotNull(teacherService.save(request).getName()),
                () -> assertNotEquals(teacherService.save(request).getName(), "Mocking"));

    }

    @Test
    void saveThrows() {
        when(teacherRepository.existsByNameAndAndDegree(anyString(), anyString())).thenReturn(false);
        assertThrows(ObjectAlreadyExistException.class, () -> teacherService.save(request));
    }

    @Test
    void updateDegree() throws ObjectNotFoundException {
        when(teacherRepository.existsById(anyLong())).thenReturn(true);
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.ofNullable(teacher));
        assertAll(
                () -> assertEquals(teacherService.updateDegree(1L, "MSc").getDegree(), "MSc"),
                () -> assertTrue(teacherService.updateDegree(1L, "MSc").equals(TeacherResponse.builder()
                        .degree("MSc")
                        .id(1L)
                        .name("Christian")
                        .build())));


    }
}