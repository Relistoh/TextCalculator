package com.github.relistoh.text_calculator;

public interface TextFile {
    String readData(String fileName);
    void writeData(String data, String fileName);
}
