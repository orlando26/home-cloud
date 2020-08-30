package com.orlando.quesadillacloud.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.orlando.quesadillacloud.util.FS;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    
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

}