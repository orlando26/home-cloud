package com.orlando.quesadillacloud.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Directory {

    private String name;

    private String lastModified;

    @Builder
    public Directory(String name, String lastModified) {
        this.name = name;
        this.lastModified = lastModified;
    }
    
}