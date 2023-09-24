package com.schoolofliberation.academic.Services;


import org.springframework.http.ResponseEntity;

import com.schoolofliberation.academic.dto.StudentDTO;

public interface StudentService {
    
    ResponseEntity<Object>  getStudents(Integer page, Integer size, String name, String orientation, String orderBy);

    ResponseEntity<String> deleteStudent(Long id);

    ResponseEntity<String> createStudent(StudentDTO student);

    ResponseEntity<String> updateStudent(Long id, StudentDTO studentDTO);

    ResponseEntity<Object> getStudent(Long id);

    ResponseEntity<Object> getdeleteStudents(Integer page, Integer size,String orientation, String orderBy);
}
