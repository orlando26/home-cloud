package com.orlando.quesadillacloud.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class File {
    
    private String name;

    private String size;

    private String extension;

    private String lastModified;

    @Builder
    public File(String name, String size, String extension, String lastModified) {
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.lastModified = lastModified;
    }

}