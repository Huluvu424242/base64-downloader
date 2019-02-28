package com.github.funthomas424242.base64downloader.service;

import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Service
public class DownloadService {


    public ResponseEntity<byte[]> fetchFile(final String url) throws IOException {

        System.out.println("Beginne Download von:"+url);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
            new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
            url,
            HttpMethod.GET, entity, byte[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
//            Files.write(Paths.get("google.png"), response.getBody());
            return response;
        } else {
            return null;
        }

    }
}
