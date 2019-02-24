package com.github.funthomas424242.base64downloader.web;

import com.github.funthomas424242.base64downloader.service.DownloadService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.net.URL;

@RestController
public class Base64DownloadController {

    @Inject
    protected DownloadService downloadService;

    @GetMapping("/getInfo")
    public String getInfo(){
        return "Der Service ist: "+downloadService;
    }

    @GetMapping(path = "/getResource/{url}", produces= MediaType.TEXT_PLAIN_VALUE)
    public String getResource(@PathVariable URL url){


        return "Der Download URL ist: "+url;
    }
}
