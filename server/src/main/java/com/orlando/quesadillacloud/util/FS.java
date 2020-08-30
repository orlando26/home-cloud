package com.orlando.quesadillacloud.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.orlando.quesadillacloud.dto.Content;
import com.orlando.quesadillacloud.dto.Directory;
import com.orlando.quesadillacloud.dto.File;

public class FS {

    public static String homeDirectory = System.getProperty("user.home") + "/app";

    public static String mkdir(String path) {
        try {
            Shell.command("mkdir " + homeDirectory + path);
            return "Folder created!";
        } catch (Exception e) {
            return "Error removing folder";
        }
    }

    public static String rm(String path) {
        try {
            Shell.command("rm -rf " + homeDirectory + path);
            return "Folder removed!";
        } catch (Exception e) {
            return "Error removing folder";
        }
    }

    public static String rmFile(String path) {
        try {
            Shell.command("rm " + homeDirectory + path);
            return "File removed!";
        } catch (Exception e) {
            return "Error removing file";
        }
    }

    public static Content ls(String path) {
        return Content.builder()
        .path(path)
        .directories(getDirectories(path))
        .files(getFiles(path))
        .build();
    }

    private static List<Directory> getDirectories(String path) {
        List<Directory> directories = new ArrayList<>();
        String result = Shell.command("ls -plh " + homeDirectory + path + " | grep /");
        List<String> lines = Arrays.asList(result.split("\n"));
        lines.forEach(line -> {
            try {
                String[] props = line.split("\\s+");
                String date = props[5] + " " + props[6] + " " + props[7];
                Directory dir = Directory.builder()
                .name(props[8])
                .lastModified(date)
                .build();
                directories.add(dir);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

        });
        return directories;
    }

    private static List<File> getFiles(String path) {
        List<File> files = new ArrayList<>();
        String result = Shell.command("ls -plh " + homeDirectory + path + " | grep -v total | grep -v /");
        List<String> lines = Arrays.asList(result.split("\n"));
        lines.forEach(line -> {
            try {
                String[] props = line.split("\\s+");
                String date = props[5] + " " + props[6] + " " + props[7];
                File file = File.builder()
                .size(props[4])
                .name(props[8])
                .extension(props[8].split("\\.")[1])
                .lastModified(date)
                .build();
                files.add(file);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

        });
        return files;
    }
}