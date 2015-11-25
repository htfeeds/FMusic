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
        
        String fullPath = location + file.getOriginalFilename();
        FileCopyUtils.copy(file.getBytes(), new File(fullPath));
        
        return fullPath;
    }
}
