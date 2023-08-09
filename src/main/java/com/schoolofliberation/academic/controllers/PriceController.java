package com.schoolofliberation.academic.controllers;

import com.schoolofliberation.academic.Services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {

    @Autowired
    PriceService priceService;

    @GetMapping("/price")
    public ResponseEntity<Object> findAllActualPrice(){
        return priceService.getAllPrice();
    }
}
