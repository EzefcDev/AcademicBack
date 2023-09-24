package com.schoolofliberation.academic.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.schoolofliberation.academic.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    
    @Query(value = "select * from students where students.name = :name and students.delete_at is null", nativeQuery = true)
    Page<Student> findByNameWithoutDeleteAt(@Param("name") String name, Pageable pageable);

    @Query(value = "select * from students where students.delete_at is null", nativeQuery = true)
    Page<Student> findAllWithoutDeleteAt(Pageable pageable);

    @Query(value = "select count(*) > 0 from students where students.dni = :dni", nativeQuery = true)
    boolean existsByDni(@Param("dni") Long dni);

    @Query(value = "select * from students where students.id = :id and students.delete_at = '2000-01-01'", nativeQuery = true)
    Optional<Student> findByIdWithoutDeleteAt(@Param("id") Long id);
}
