package com.schoolofliberation.academic.Services;


import org.springframework.http.ResponseEntity;

import com.schoolofliberation.academic.dto.StudentDTO;

public interface StudentService {
    
    ResponseEntity<Object>  getStudents(Integer page, Integer size, String name);

    ResponseEntity<String> deleteStudent(Long id);

    ResponseEntity<String> createStudent(StudentDTO student);

    ResponseEntity<String> updateStudent(Long id, StudentDTO studentDTO);
}
