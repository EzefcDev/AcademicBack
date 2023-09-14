package com.schoolofliberation.academic.Services;

import com.schoolofliberation.academic.Repositories.StudentRepository;
import com.schoolofliberation.academic.converters.StudentConverter;
import com.schoolofliberation.academic.dto.StudentDTO;
import com.schoolofliberation.academic.entities.Student;
import com.schoolofliberation.academic.entities.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    StudentConverter studentConverter;
    @InjectMocks
    private StudentImplService studentImplService;

    private Student student;

    private StudentDTO studentDTO;

    private Type type;
    @BeforeEach
    void setUp() {
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

    @DisplayName("Test for create student")
    @Test
    void createStudent(){
        //given
        given(studentRepository.existsByDni(studentDTO.getDni())).willReturn(true);
        //when
        ResponseEntity<String> student1 = studentImplService.createStudent(studentDTO);
        //then
        assertThat(student1.getStatusCodeValue()).isEqualTo(201);
    }
}
