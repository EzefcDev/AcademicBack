package com.schoolofliberation.academic.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.schoolofliberation.academic.Services.StudentService;
import com.schoolofliberation.academic.dto.StudentDTO;

@RestController
@Validated
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<Object> findAllStudents(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(required = false, name="name", defaultValue = "") String name){
        return studentService.getStudents(page, size, name);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") @Min(1) Long id){
        return studentService.deleteStudent(id);
    }

    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody @Valid StudentDTO newStudent){
        return studentService.createStudent(newStudent);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable(name = "id") @Min(1) Long id, @RequestBody @Valid StudentDTO studentDTO){
        return studentService.updateStudent(id, studentDTO);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Object> findStudent(@PathVariable(name = "id") @Min(1) Long id){
        return studentService.getStudent(id);
    } 
}
