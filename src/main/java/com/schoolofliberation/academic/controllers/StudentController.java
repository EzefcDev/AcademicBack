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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Maneja estudiantes", description = "Operaciones pertinentes para manejar el registro de estudiantes")
public class StudentController {
    
    @Autowired
    StudentService studentService;

    @Operation(summary = "Trae todos los estudiantes del sistema", description = "Muestra la lista de todos los estudiantes registrados a traves de un paginado, también podemos indicar un nombre y nos mostrar la lista de todas las coincidencias con ese nombre")
    @GetMapping("/students")
    public ResponseEntity<Object> findAllStudents(
        @RequestParam(defaultValue = "0") Integer page, 
        @RequestParam(defaultValue = "10") Integer size, 
        @RequestParam(required = false, name="name", defaultValue = "") String name,
        @RequestParam(defaultValue = "asc") String orientation,
        @RequestParam(defaultValue = "id") String orderBy){
        return studentService.getStudents(page, size, name, orientation, orderBy);
    }

    @Operation(summary = "Elimina el estudiante indicado", description = "Eliminación del estudiante a traves del id")
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "id") @Min(1) Long id){
        return studentService.deleteStudent(id);
    }

    @Operation(summary = "Crea un estudiante",description = "Creación del estudiante pasándole el nombre, apellido, dni, estado de la carrera")
    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody @Valid StudentDTO newStudent){
        return studentService.createStudent(newStudent);
    }

    @Operation(summary = "Actualiza el estudiante indicado",description = "Actualización del estudiante pasandole el id y los parámetros para actualizar")
    @PutMapping("/student/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable(name = "id") @Min(1) Long id, @RequestBody @Valid StudentDTO studentDTO){
        return studentService.updateStudent(id, studentDTO);
    }

    @Operation(summary = "Trae los datos del estudiante indicado",description = "Muestra todos los datos del alumno pasandole el id")
    @GetMapping("/student/{id}")
    public ResponseEntity<Object> findStudent(@PathVariable(name = "id") @Min(1) Long id){
        return studentService.getStudent(id);
    } 

    @Operation(summary = "Trae todos los estudiantes eliminados del sistema", description = "Muestra la lista de todos los estudiantes registrados a traves de un paginado, también podemos indicar un nombre y nos mostrar la lista de todas las coincidencias con ese nombre")
    @GetMapping("/delete-students")
    public ResponseEntity<Object> findAllDeleteStudents(
        @RequestParam(defaultValue = "0") Integer page, 
        @RequestParam(defaultValue = "10") Integer size, 
        @RequestParam(defaultValue = "asc") String orientation,
        @RequestParam(defaultValue = "id") String orderBy){
        return studentService.getdeleteStudents(page, size,orientation, orderBy);
    }
}
