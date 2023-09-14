package com.schoolofliberation.academic.Services;

import com.schoolofliberation.academic.Repositories.StudentRepository;
import com.schoolofliberation.academic.converters.StudentConverter;
import com.schoolofliberation.academic.dto.StudentDTO;
import com.schoolofliberation.academic.entities.Student;
import com.schoolofliberation.academic.entities.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
class StudentImplServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    StudentConverter studentConverter;

    @InjectMocks
    StudentImplService studentImplService;

    private Student student;

    private StudentDTO studentDTO;

    private Type type;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        type = Type.builder()
                .value(1L)
                .meaning("Cursando")
                .type("student_status")
                .build();

        student = Student.builder()
                .id(1L)
                .name("Alejando")
                .lastname("Maldonado")
                .dni(30345789L)
                .studentStatus(type)
                .studentCareer((short) 1)
                .build();

        studentDTO = StudentDTO.builder()
                .name("Eze")
                .lastname("Ferra")
                .dni(23347080L)
                .studentStatusMeaning("Cursando")
                .studentCareer((short) 1)
                .build();
    }

    @Test
    @DisplayName("Expected create student")
    void createStudent() {

        //given
        when(studentRepository.existsByDni(studentDTO.getDni())).thenReturn(false);
        when(studentConverter.dtoToEntity(studentDTO)).thenReturn(new Student());

        //when
        ResponseEntity<String> response = studentImplService.createStudent(studentDTO);

        //then
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("Estudiante creado", response.getBody());
        verify(studentRepository, times(1)).existsByDni(23347080L);
        verify(studentRepository, times(1)).save(any(Student.class));
        verifyNoMoreInteractions(studentRepository);

    }

    @Test
    @DisplayName("Expected methods failures")
    void faillCreatedStudent(){

        when(studentRepository.existsByDni(studentDTO.getDni())).thenReturn(true);

        ResponseEntity<String> response = studentImplService.createStudent(studentDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Estudiante ya existe", response.getBody());
        verify(studentRepository, times(1)).existsByDni(23347080L);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    @DisplayName("Expected get one student")
    void getStudent() {

        given(studentRepository.findById(1L)).willReturn(Optional.of(student));

        ResponseEntity<Object> studentTest = studentImplService.getStudent(1L);

        assertEquals(HttpStatus.OK , studentTest.getStatusCode());
        assertEquals(student, studentTest.getBody());
        verify(studentRepository,times(1)).findById(1L);
        verifyNoMoreInteractions(studentRepository);
    }
    @Test
    @DisplayName("Expected methods failures")
    void notGetStudent() {

        given(studentRepository.findById(2L)).willReturn(Optional.empty());

        ResponseEntity<Object> studentTest = studentImplService.getStudent(2L);

        assertEquals(HttpStatus.BAD_REQUEST , studentTest.getStatusCode());
        assertEquals("El estudiante con id: 2 no existe", studentTest.getBody());
        verify(studentRepository,times(1)).findById(2L);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void deleteStudent() {

        when(studentRepository.findById(1L)).thenReturn(Optional.ofNullable(student));

        ResponseEntity<String> response = studentImplService.deleteStudent(student.getId());

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("El estudiante se elimino correctamente", response.getBody());
        verify(studentRepository,times(1)).deleteById(1L);
    }

    @Test
    void failDeleteStudent() {

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<String> response = studentImplService.deleteStudent(student.getId());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Estudiante no existe con ese id o ya fue eliminado", response.getBody());
        verify(studentRepository,times(0)).deleteById(1L);
    }

    @Test
    void updateStudent() {
    }
}