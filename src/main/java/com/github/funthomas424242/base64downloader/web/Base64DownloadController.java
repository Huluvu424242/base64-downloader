package com.github.funthomas424242.base64downloader.web;

import com.github.funthomas424242.base64downloader.service.DownloadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.IOException;

@RestController
public class Base64DownloadController {

    @Inject
    protected DownloadService downloadService;

    @GetMapping("/getInfo")
    public String getInfo(){
        return "Der Service ist: "+downloadService;
    }

    @GetMapping(path = "/getResource", produces= MediaType.TEXT_PLAIN_VALUE)
    public String getResource(@RequestParam String url) throws IOException {
//
//        final ResponseEntity<byte[]> entity = downloadService.fetchFile(url);
//        entity.getBody();
//        return entity.getBody().toString();
        final ResponseEntity<byte[]> entity = downloadService.fetchFile(url);
        final byte[] content = entity.getBody();
        final String text = new String(content);
//        final String text = "Hallo Du da";
        return text;

    }
}
