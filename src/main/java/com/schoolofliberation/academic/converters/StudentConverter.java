package com.schoolofliberation.academic.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schoolofliberation.academic.Repositories.TypeRepository;
import com.schoolofliberation.academic.dto.StudentDTO;
import com.schoolofliberation.academic.entities.Student;

@Component
public class StudentConverter {

    @Autowired
    TypeRepository typeRepository;
    
    @Autowired
    ModelMapper modelMapper;

    public Student dtoToEntity(StudentDTO dto){
        Student student = modelMapper.map(dto, Student.class);
        student.setStudentStatus(typeRepository.findByMeaningContaining(dto.getStudentStatusMeaning()));
        return student;
    }

    public StudentDTO entityToDTO(Student student){
       StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
       studentDTO.setStudentStatusMeaning(student.getStudentStatus().getMeaning());
       return studentDTO;
    }
}
