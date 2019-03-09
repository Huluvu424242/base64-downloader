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

    @GetMapping(path = "/download.zip", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getResource(@RequestParam String url) throws IOException {

        final ResponseEntity<byte[]> entity = downloadService.fetchFile(url);
        final byte[] content = entity.getBody();
        return ResponseEntity
            .status(entity.getStatusCode())
            .body(downloadService.convertBinary2Base64("download.base64", content));

    }


}
