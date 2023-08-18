package com.schoolofliberation.academic.Services;

import com.schoolofliberation.academic.Repositories.PriceRepository;
import com.schoolofliberation.academic.entities.Price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    PriceRepository priceRepository;
    public ResponseEntity<Object> getAllPrice(){
        Double dollarPrice = getDollar();
        List<Price> prices = priceRepository.findAll();
        List<Price> actualPrices = new ArrayList<>();
        for (Price price : prices) {
            Double priceCareer = price.getPriceDollar() * dollarPrice ;
            price.setPriceCareer(priceCareer);
            actualPrices.add(price);
        }
        return new ResponseEntity<>(actualPrices,HttpStatus.OK);
    }

    public Double getDollar(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.bluelytics.com.ar/v2/latest";
        HashMap<String, HashMap<String, Double>> data = restTemplate.getForObject(url, HashMap.class);
        if(data != null){
          return data.get("blue").get("value_sell"); 
        }
        return 0.0;
    }
}
