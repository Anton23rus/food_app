package com.example.food_rest.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
//@SuppressWarnings("HideUtilityClassConstructor")
public class CsvWorkerUtil {

    public static List<String> readCSVFile(final String filePath, final boolean skipFirstRow) {
        if (!filePath.endsWith(".csv")) {
            log.error("Error: Incorrect file format: {}", filePath);
            return Collections.emptyList();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            if (skipFirstRow) {
                return br.lines().skip(1).collect(Collectors.toList());
            }
            return br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.toString());
            return Collections.emptyList();
        }
    }

    public void writeToCsv(final String filePath, List<String> data, boolean append) {
        try (CsvFileWriter bufferedWriter = new CsvFileWriter(new FileWriter(filePath, append))) {
            data.stream()
                    .forEach(row -> {
                        bufferedWriter.write(row);
                        bufferedWriter.newLine();
                    });
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

}
