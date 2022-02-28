package com.example.food_rest.utils;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.Writer;

public class CsvFileWriter extends BufferedWriter {
    public CsvFileWriter(Writer out) {
        super(out);
    }

    public CsvFileWriter(Writer out, int sz) {
        super(out, sz);
    }

    @SneakyThrows
    @Override
    public void newLine() {
        super.newLine();
    }

    @SneakyThrows
    @Override
    public void write(String str) {
        super.write(str);
    }
}
