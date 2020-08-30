package com.orlando.quesadillacloud.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Content {

    private String path;
    
    private List<Directory> directories;

    private List<File> files;

    @Builder
    public Content(String path, List<Directory> directories, List<File> files) {
        this.path = path;
        this.directories = directories;
        this.files = files;
    }    

}