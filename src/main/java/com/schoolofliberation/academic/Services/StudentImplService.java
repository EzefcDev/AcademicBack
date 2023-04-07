package com.schoolofliberation.academic.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolofliberation.academic.Repositories.*;
import com.schoolofliberation.academic.dto.StudentDTO;
import com.schoolofliberation.academic.entities.*;

@Service
public class StudentImplService implements StudentService {
    
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TypeRepository typeRepository;

    @Override
    public ResponseEntity<Object>  getStudents(Integer page, Integer size, String name){
        Pageable pageable = PageRequest.of(page, size);
        if (name.isEmpty()) {
            Page<Student> listStudents = studentRepository.findAll(pageable);
            return new ResponseEntity<>(listStudents,HttpStatus.OK);
        } else{
            Page<Student> listSearch = studentRepository.findByNameContaining(name, pageable);
            if (listSearch.isEmpty()) {
                return new ResponseEntity<>(listSearch,HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(listSearch,HttpStatus.OK);
            }
        }
    }

    public ResponseEntity<String> deleteStudent(Long id){
        boolean studentExist = studentRepository.findById(id).isPresent();
        if (studentExist) {
            studentRepository.deleteById(id);
            return new ResponseEntity<>("El estudiante se elemino correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Estudiante no existe con ese id o ya fue eliminado", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> createStudent(StudentDTO newStudent){
        if (studentRepository.existsByDni(newStudent.getDni())) {
            return new ResponseEntity<>("Estudiante ya existe", HttpStatus.BAD_REQUEST);
        }
        Student student = new Student();
        student.setDni(newStudent.getDni());
        student.setName(newStudent.getName());
        student.setLastname(newStudent.getLastname());
        student.setStudentCareer(newStudent.getStudentCareer());
        student.setStudentStatus(typeRepository.findByMeaningContaining(newStudent.getStudentStatusMeaning()));
        studentRepository.save(student);
        return new ResponseEntity<>("Estudiante creado", HttpStatus.OK);
    }

    public ResponseEntity<String> updateStudent(Long id, StudentDTO studentDTO){
        boolean studentExist = studentRepository.existsById(id);
        if (!studentExist) {
            return new ResponseEntity<>("El estudiante con id: " + id + " no esxite", HttpStatus.BAD_REQUEST);
        }
        Student updateStudent = studentRepository.findById(id).get();
        updateStudent.setName(studentDTO.getName());
        updateStudent.setLastname(studentDTO.getLastname());
        updateStudent.setDni(studentDTO.getDni());
        updateStudent.setStudentCareer(studentDTO.getStudentCareer());
        updateStudent.setStudentStatus(typeRepository.findByMeaningContaining(studentDTO.getStudentStatusMeaning()));
        studentRepository.save(updateStudent);
        return new ResponseEntity<>("El estudiante se actualizo correctamente", HttpStatus.OK);
    }

    public ResponseEntity<Object> getStudent(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return new ResponseEntity<>(student.get() ,HttpStatus.OK);
        }
        return new ResponseEntity<>("El estudiante con id: " + id + " no esxite", HttpStatus.BAD_REQUEST);
    }
}
