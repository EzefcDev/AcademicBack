package com.schoolofliberation.academic.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolofliberation.academic.entities.Type;

public interface TypeRepository extends JpaRepository<Type,Long> {
    
    Type findByMeaningContaining(String meaning); 
}
