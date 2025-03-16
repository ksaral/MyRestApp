package com.example.demo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SecretReader {
    public static String readSecret(String filePath, String defaultValue) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath))).trim();
        } catch (IOException e) {
            return defaultValue; // Fallback to default if secret file is missing
        }
    }
}