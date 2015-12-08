package com.htf.fmusic.utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author HTFeeds
 */
public class FmusicFunctions {
    private static final Logger LOGGER = LoggerFactory.getLogger(FmusicFunctions.class);

    public static String uploadImage(MultipartFile file, String location) throws IOException {
        LOGGER.info("Uploading Image...");

        //checking directory exists
        if (!new File(location).isDirectory()) {
            LOGGER.info("Creating directory: {}", location);
            new File(location).mkdir();
        }

        //Split file name and it's extension.
		String fileName = file.getOriginalFilename();
        String extension = "";
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            extension = fileName.substring(pos, fileName.length());
            fileName = fileName.substring(0, pos);
        }

        File f = new File(location + fileName + extension);
        int i = 1;

        while (f.exists()) {
            //If file is exists, change file name.
            LOGGER.info("File name is exists: {}", f.getName());
            String newName = fileName + "_" + i;
            f = new File(location + newName + extension);
            i++;
        }

        FileCopyUtils.copy(file.getBytes(), f);

        LOGGER.info("Uploaded successfully.");
        return f.getName();
    }
}
