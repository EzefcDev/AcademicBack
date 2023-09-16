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
import com.schoolofliberation.academic.converters.StudentConverter;
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

    @Autowired
    StudentConverter studentConverter;

    private static final String STUDENT_NO_EXIST = "No existe el estudiante con id: ";

    @Override
    public ResponseEntity<Object> getStudents(Integer page, Integer size, String name, String orientation, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orientation),orderBy));
        if (!name.isEmpty()) {
            Page<Student> listSearch = studentRepository.findByNameContaining(name, pageable);
            if (!listSearch.isEmpty()) {
                log.info("Devolución de alumnos con parámetro de búsqueda");
                return new ResponseEntity<>(listSearch,HttpStatus.OK);
            } 
            log.error("no se encontraron alumnos, lista vacía");
            return new ResponseEntity<>(listSearch,HttpStatus.BAD_REQUEST);
        } 
        Page<Student> listStudents = studentRepository.findAll(pageable);
        log.info("Devolución de alumnos sin parámetro de búsqueda");
        return new ResponseEntity<>(listStudents,HttpStatus.OK);
    }

    public ResponseEntity<String> deleteStudent(Long id){
        boolean studentExist = studentRepository.findById(id).isPresent();
        if (!studentExist) {
            log.error(STUDENT_NO_EXIST + id);
            return new ResponseEntity<>("Estudiante no existe con ese id o ya fue eliminado", HttpStatus.BAD_REQUEST);
        }
        studentRepository.deleteById(id);
        log.info("Eliminación del estudiante con id: " + id);
        return new ResponseEntity<>("El estudiante se elimino correctamente", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> createStudent(StudentDTO newStudent){
        if (studentRepository.existsByDni(newStudent.getDni())) {
            if (isEqual(newStudent)) {
                log.error("El estudiante con dni: " + newStudent.getDni() + " ya existe");
                return new ResponseEntity<>("Estudiante ya existe", HttpStatus.BAD_REQUEST);   
            } else {
                log.error("El estudiante con dni: " + newStudent.getDni() + " ya existe, pero sus datos son diferentes");
                return new ResponseEntity<>("Estudiante ya existe, pero sus datos son diferentes, debe utilizar el metodo actulizar", HttpStatus.BAD_REQUEST);    
            }
        }
        studentRepository.save(studentConverter.dtoToEntity(newStudent));
        log.info("El estudiante se creo correctamente");
        return new ResponseEntity<>("Estudiante creado", HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateStudent(Long id, StudentDTO studentDTO){
        boolean studentExist = studentRepository.existsById(id);
        if (!studentExist) {
            log.error(STUDENT_NO_EXIST + id);
            return new ResponseEntity<>("El estudiante con id: " + id + " no existe", HttpStatus.BAD_REQUEST);
        }
        Student updateStudent = studentConverter.dtoToEntity(studentDTO);
        updateStudent.setId(id);
        studentRepository.save(updateStudent);
        log.info("Estudiante actualizado correctamente");
        return new ResponseEntity<>("El estudiante se actualizo correctamente", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Object> getStudent(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            log.info("Estudiante esta presente, se muestran sus datos");
            return new ResponseEntity<>(student.get() ,HttpStatus.OK);
        }
        log.error(STUDENT_NO_EXIST + id);
        return new ResponseEntity<>("El estudiante con id: " + id + " no existe", HttpStatus.BAD_REQUEST);
    }

    public boolean isEqual(StudentDTO dto ){
        StudentDTO studentDto = studentConverter.entityToDTO(studentRepository.findByDni(dto.getDni()));
        return dto.equals(studentDto);
    }
}
