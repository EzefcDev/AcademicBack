package com.schoolofliberation.academic.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.schoolofliberation.academic.entities.Student;
import com.schoolofliberation.academic.entities.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    private Type type;

    @BeforeEach
    void setup(){
        type = Type.builder()
            .value(1L)
            .meaning("Cursando")
            .type("student_status")
            .build();

        student = Student.builder()
            .name("Ezequiel")
            .lastname("Martinez")
            .dni(33228564L)
            .studentStatus(type)
            .studentCareer((short) 1)
            .build();
    }

    @DisplayName("Test for save one student")
    @Test
    void save(){

        //given
        Type type = Type.builder()
                .value(1L)
                .meaning("Cursando")
                .type("student_status")
                .build();

        Student student1 = Student.builder()
                .name("Ezequiel")
                .lastname("Martinez")
                .dni(33228564L)
                .studentStatus(type)
                .studentCareer((short) 1)
                .build();

        //when
        Student studentSave = studentRepository.save(student1);

        //then
        assertThat(studentSave).isNotNull();
        assertThat(studentSave.getId()).isGreaterThan(0);
    }

    @DisplayName("Test to verify if there is one student")
    @Test
    void existsByDni(){
        //given
        Long dni = 32345781L;

        //when
        boolean existStudent = studentRepository.existsByDni(dni);

        //then
        assertThat(existStudent).isEqualTo(true);

    }

    @DisplayName("Test to verify searching student for student name")
    @Test
    void findByNameContaining(){

        //given

        String studentName = "Ramiro";
        Integer page = 0;
        Integer size = 10;
        String orientation = "asc";
        String orderBy = "id";

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orientation),orderBy));

        //when
        Page<Student> listStudent = studentRepository.findByNameContaining(studentName, pageable);

        //then
        assertThat(listStudent).isNotNull();
//        assertThat(listStudent).isNotEmpty();
        assertThat(listStudent.getTotalElements()).isEqualTo(10);
    }
}
