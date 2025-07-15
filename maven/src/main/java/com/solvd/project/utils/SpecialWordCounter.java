package com.solvd.project.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class SpecialWordCounter {

    public static void analyzeFromResources(String articleName, String resultName) throws IOException {
        ClassLoader classLoader = SpecialWordCounter.class.getClassLoader();

        // Load article.txt from resources
        URL articleUrl = classLoader.getResource(articleName);
        if (articleUrl == null) {
            throw new FileNotFoundException("Resource not found: " + articleName);
        }
        File articleFile = new File(articleUrl.getFile());

        // Read and analyze
        String content = FileUtils.readFileToString(articleFile, StandardCharsets.UTF_8);
        String[] words = StringUtils.split(content, " \n\t\r.,;:!?()[]{}\"'");
        Pattern specialPattern = Pattern.compile("^[A-Za-z]{8,}$");

        int specialCount = 0;
        for (String word : words) {
            if (specialPattern.matcher(word).matches()) {
                specialCount++;
            }
        }

        // Save result to results.txt (append)
        File resultFile = new File("results.txt"); // still writes to root folder
        String result = "\n--- Special Word Count ---\n" +
                "File: " + articleName + "\n" +
                "Special words (8+ letters): " + specialCount + "\n";

        FileUtils.writeStringToFile(resultFile, result, StandardCharsets.UTF_8, true);
    }
}