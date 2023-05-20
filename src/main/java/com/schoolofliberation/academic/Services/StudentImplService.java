package com.schoolofliberation.academic.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolofliberation.academic.Repositories.*;
import com.schoolofliberation.academic.dto.StudentDTO;
import com.schoolofliberation.academic.entities.*;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentImplService implements StudentService {
    
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TypeRepository typeRepository;

    @Override
    public ResponseEntity<Object>  getStudents(Integer page, Integer size, String name, String orientation){
        // Sort.Direction direction = orientation.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC ;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orientation),"id"));
        if (!name.isEmpty()) {
            Page<Student> listSearch = studentRepository.findByNameContaining(name, pageable);
            if (!listSearch.isEmpty()) {
                log.info("Devolucion de alumnos con parametro de busqueda");
                return new ResponseEntity<>(listSearch,HttpStatus.OK);
            } 
            log.error("no se encontraron alumnos, lista vacia");
            return new ResponseEntity<>(listSearch,HttpStatus.BAD_REQUEST);
        } 
        Page<Student> listStudents = studentRepository.findAll(pageable);
        log.info("Devolucion de alumnos sin parametro de busqueda");
        return new ResponseEntity<>(listStudents,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteStudent(Long id){
        boolean studentExist = studentRepository.findById(id).isPresent();
        if (!studentExist) {
            log.error("No existe el estudiante con id: " + id);
            return new ResponseEntity<>("Estudiante no existe con ese id o ya fue eliminado", HttpStatus.BAD_REQUEST);
        }
        studentRepository.deleteById(id);
        log.info("Eliminación del estudiante con id: " + id);
        return new ResponseEntity<>("El estudiante se elemino correctamente", HttpStatus.OK);
    }

    public ResponseEntity<String> createStudent(StudentDTO newStudent){
        if (studentRepository.existsByDni(newStudent.getDni())) {
            log.error("El estudiante con dni: " + newStudent.getDni() + " ya existe");
            return new ResponseEntity<>("Estudiante ya existe", HttpStatus.BAD_REQUEST);
        }
        Student student = new Student();
        student.setDni(newStudent.getDni());
        student.setName(newStudent.getName());
        student.setLastname(newStudent.getLastname());
        student.setStudentCareer(newStudent.getStudentCareer());
        student.setStudentStatus(typeRepository.findByMeaningContaining(newStudent.getStudentStatusMeaning()));
        studentRepository.save(student);
        log.info("El estudiante se creo correctamente");
        return new ResponseEntity<>("Estudiante creado", HttpStatus.OK);
    }

    public ResponseEntity<String> updateStudent(Long id, StudentDTO studentDTO){
        boolean studentExist = studentRepository.existsById(id);
        if (!studentExist) {
            log.error("No existe el estudiante con id: " + id);
            return new ResponseEntity<>("El estudiante con id: " + id + " no esxite", HttpStatus.BAD_REQUEST);
        }
        Student updateStudent = studentRepository.findById(id).get();
        updateStudent.setName(studentDTO.getName());
        updateStudent.setLastname(studentDTO.getLastname());
        updateStudent.setDni(studentDTO.getDni());
        updateStudent.setStudentCareer(studentDTO.getStudentCareer());
        updateStudent.setStudentStatus(typeRepository.findByMeaningContaining(studentDTO.getStudentStatusMeaning()));
        studentRepository.save(updateStudent);
        log.info("Estudiante actualizado correctamente");
        return new ResponseEntity<>("El estudiante se actualizo correctamente", HttpStatus.OK);
    }

    public ResponseEntity<Object> getStudent(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            log.info("Estudiante esta presente, se muestran sus datos");
            return new ResponseEntity<>(student.get() ,HttpStatus.OK);
        }
        log.error("No existe el estudiante con id: " + id);
        return new ResponseEntity<>("El estudiante con id: " + id + " no esxite", HttpStatus.BAD_REQUEST);
    }
}
