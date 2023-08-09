package com.schoolofliberation.academic.Repositories;

import com.schoolofliberation.academic.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Long> {
}
