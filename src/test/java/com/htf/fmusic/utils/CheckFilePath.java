package com.htf.fmusic.utils;

import java.io.File;

/**
 * @author HTFeeds
 */
public class CheckFilePath {
    public static void main(String[] args) {
        String path = "E:/Upload";

        //checking directory exists
        if (!new File(path).isDirectory()) {
            System.out.println("Creating directory: " + path);
            new File(path).mkdir();
            System.out.println("Done");
        } else {
            System.out.println("Directory is exists.");
        }

        String fullPath = "E:/Upload/js.jpg";
        File f = new File(fullPath);

        //checking file exists
        if (f.exists() && !f.isDirectory()) {
            System.out.println("File is exists.");
        } else {
            System.out.println("File is not exists.");
        }
    }
}
