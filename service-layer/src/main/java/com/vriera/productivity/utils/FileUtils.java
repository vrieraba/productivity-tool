package com.vriera.productivity.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class FileUtils {

    private static final String FILES_PATH = "/mnt/ProductivityToolData/";

    public List<File> getFiles() {
        File[] filesArray = new File(FILES_PATH).listFiles();
        if (filesArray != null) {
            return Arrays.asList(filesArray);
        } else {
            return Collections.emptyList();
        }
    }

    public InputStream getInputSteam(String fileName) throws IOException {
        return Files.newInputStream(Paths.get(FILES_PATH + fileName));
    }
}
