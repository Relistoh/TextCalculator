package com.github.relistoh.text_calculator;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface TextFile {
    String readData(String fileName) throws IOException;
    void writeData(String data, String fileName);
}
