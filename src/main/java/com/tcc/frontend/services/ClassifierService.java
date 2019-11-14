package com.tcc.frontend.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class ClassifierService {

    RestTemplate http;
    HttpHeaders headers;


    public ClassifierService() {
        this.http = new RestTemplate();
        this.headers = null;

    }

    public String classify(MultipartFile file){
        try {
            HttpEntity requestEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity;

            responseEntity = this.http.exchange("sdafasdfasdf",
                    HttpMethod.GET,
                    requestEntity,
                    String.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            System.out.println(e);    
            return null;
        }
    }
}