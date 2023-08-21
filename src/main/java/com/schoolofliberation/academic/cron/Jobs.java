package com.schoolofliberation.academic.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.schoolofliberation.academic.Services.PriceService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Jobs {
    
    @Autowired
    PriceService priceService;

    @Scheduled(cron = "${cron.expression}")
    public void job(){
        priceService.jobPrice();
        log.info("Pricios actualizados");
    }
}
