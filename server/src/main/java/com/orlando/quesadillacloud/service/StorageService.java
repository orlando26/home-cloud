package com.orlando.quesadillacloud.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import com.orlando.quesadillacloud.util.FS;
import com.orlando.quesadillacloud.util.MediaTypeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    @Autowired
    private ServletContext servletContext;
    
    public String upload(MultipartFile file, String folder) {
        if (file.isEmpty()) {
            return "File is empty!";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FS.homeDirectory + folder + "/" + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "File uploaded!";
    }

    public ResponseEntity<InputStreamResource> downloadFile(String fileName, String folder) throws IOException {

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