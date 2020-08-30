package com.orlando.quesadillacloud.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import com.orlando.quesadillacloud.dto.Content;
import com.orlando.quesadillacloud.service.StorageService;
import com.orlando.quesadillacloud.util.FS;
import com.orlando.quesadillacloud.util.MediaTypeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/cloud")
public class CloudController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/ls")
    @CrossOrigin
    public ResponseEntity<Content> ls(@RequestParam(defaultValue = "/") String path) {
        return new ResponseEntity<>(FS.ls(path), HttpStatus.OK);
    }

    @GetMapping("/mkdir")
    @CrossOrigin
    public ResponseEntity<String> createFolder(@RequestParam(defaultValue = "/") String path) {
        return new ResponseEntity<>(FS.mkdir(path), HttpStatus.OK);
    }

    @GetMapping("/rm")
    @CrossOrigin
    public ResponseEntity<String> removeFolder(@RequestParam(defaultValue = "/") String path) {
        return new ResponseEntity<>(FS.rm(path), HttpStatus.OK);
    }

    @GetMapping("/rmFile")
    @CrossOrigin
    public ResponseEntity<String> removeFile(@RequestParam(defaultValue = "/") String path) {
        return new ResponseEntity<>(FS.rmFile(path), HttpStatus.OK);
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public ResponseEntity<String> singleFileUpload(@RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "/") String folder) {
        return new ResponseEntity<>(storageService.upload(file, folder), HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String fileName, @RequestParam(defaultValue = "/") String folder) throws IOException {

    MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

    File file = new File(FS.homeDirectory + folder + "/" + fileName);
    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    return ResponseEntity.ok()
    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
    .contentType(mediaType)
    .contentLength(file.length())
    .body(resource);
    }

}