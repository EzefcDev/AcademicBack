package com.schoolofliberation.academic.Services;

import com.schoolofliberation.academic.Repositories.PriceRepository;
import com.schoolofliberation.academic.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;
    public ResponseEntity<Object> getAllPrice(){
        List<Price> prices = priceRepository.findAll();
        return new ResponseEntity<>(prices,HttpStatus.OK);
    }
}
