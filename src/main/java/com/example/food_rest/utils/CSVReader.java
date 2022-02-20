package com.example.food_rest.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
@Slf4j
public class CSVReader {

    static final String COMMA_DELIMITER = ",";
    static final URL PATH = CSVReader.class.getClassLoader().getResource("data");

    public static void main(String[] args) {
        String filePath = PATH.getPath().concat("/Food.csv");
        List<String> test = readCSVFile(filePath);
        System.out.println(test);
    }

    static List<String> readCSVFile(final String filename, boolean skipFirstRow) {
        List<String> records = new ArrayList<>();

        if (!filename.endsWith(".csv")) {
            log.error("Error: Incorrect file format: {}", filename);
            return Collections.emptyList();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            log.error(e.toString());
            return Collections.emptyList();
        }
        return records;
    }
}
