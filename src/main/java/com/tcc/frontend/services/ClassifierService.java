package com.tcc.frontend.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.tcc.frontend.model.ResultModel;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

public class ClassifierService {

    RestTemplate http;
    HttpHeaders headers;


    public ClassifierService() {
        this.http = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        // this.headers.setAccept(acceptableMediaTypes);

    }

    public ResultModel classify(final MultipartFile file) {
        try {

            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("file", new MultipartInputStreamFileResource(file.getInputStream(), file.getOriginalFilename()) );


            final HttpEntity requestEntity = new HttpEntity<>(map, headers);
            ResponseEntity<ResultModel> responseEntity;


            responseEntity = this.http.exchange(
                "http://localhost:9092/classifier/single",
                HttpMethod.POST,
                requestEntity,
                ResultModel.class);

            return responseEntity.getBody();
        } catch (final Exception e) {
            System.out.println(e);
            return null;
        }
    }


    class MultipartInputStreamFileResource extends InputStreamResource {

        private final String filename;
    
        MultipartInputStreamFileResource(InputStream inputStream, String filename) {
            super(inputStream);
            this.filename = filename;
        }
    
        @Override
        public String getFilename() {
            return this.filename;
        }
    
        @Override
        public long contentLength() throws IOException {
            return -1; // we do not want to generally read the whole stream into memory ...
        }
    }
    
}