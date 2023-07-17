package com.schoolofliberation.academic.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schoolofliberation.academic.Repositories.TypeRepository;
import com.schoolofliberation.academic.dto.StudentDTO;
import com.schoolofliberation.academic.entities.Student;

@Component
public class StudentConverter {

    @Autowired
    TypeRepository typeRepository;
    
    public Student dtoToEntity(StudentDTO dto){
        Student student = new Student();
        student.setDni(dto.getDni());
        student.setName(dto.getName());
        student.setLastname(dto.getLastname());
        student.setStudentCareer(dto.getStudentCareer());
        student.setStudentStatus(typeRepository.findByMeaningContaining(dto.getStudentStatusMeaning()));

        return student;
    }
}
