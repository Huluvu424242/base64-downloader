package com.github.funthomas424242.base64downloader.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DownloadService {


    public ResponseEntity<byte[]> fetchFile(final String url) throws IOException {

        System.out.println("Beginne Download von:" + url);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(
            new ByteArrayHttpMessageConverter());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<byte[]> response
            = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            return null;
        }

    }

    public byte[] convertBinary2Base64(String filename, byte[] content) throws IOException {
        // encode base64
        final byte[] bytesEncoded = Base64.encodeBase64(content);
        // zip encodedBytes
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ZipOutputStream zos = new ZipOutputStream(baos);
        final ZipEntry entry = new ZipEntry(filename);
        entry.setSize(bytesEncoded.length);
        zos.putNextEntry(entry);
        zos.write(bytesEncoded);
        zos.closeEntry();
        zos.close();
        // return zipped bytes
        return baos.toByteArray();
    }
}
