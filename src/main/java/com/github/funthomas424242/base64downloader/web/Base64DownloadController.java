package com.github.funthomas424242.base64downloader.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base64DownloadController {
    @GetMapping("/")
    public String getResource(){
        return "Hello, World!!";
    }
}
