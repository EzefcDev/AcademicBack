package com.schoolofliberation.academic.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.schoolofliberation.academic.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
    Page<Student> findByNameContaining(String name, Pageable pageable);

    @Query(value = "select count(*) > 0 from students where students.dni = :dni", nativeQuery = true)
    boolean existsByDni(@Param("dni") Long dni);

    Student findByDni(Long dni);

    @Query(value = "SELECT * FROM students WHERE students.id = :id and students.delete_student is null", nativeQuery = true)
    Optional<Student> findByStudentId(@Param("id") Long id);

    @Query(value = "SELECT * FROM students WHERE students.name = :name and students.delete_student is null", nativeQuery = true)
    Page<Student> findByStudentName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM students WHERE students.delete_student is null", nativeQuery = true)
    Page<Student> findAllStudent(Pageable pageable);

    @Query(value = "SELECT * FROM students WHERE students.delete_student is not null", nativeQuery = true)
    Page<Student> findAllDeleteStudents(Pageable pageable);
}
