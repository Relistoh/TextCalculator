package com.github.relistoh.text_calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlManager implements TextFile{

    @Override
    public String readData(String fileName) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(
                objectMapper.readValue(new File(fileName + ".xml"), new TypeReference<List<Message>>() {})
        );
    }

    public String processedData(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(data, new TypeReference<List<Message>>() {});

        for (Message message : dialog) {
            message.senderMessage = ExpressionFinder.evaluateMathExpressions(message.senderMessage);
        }

        return objectMapper.writeValueAsString(dialog);
    }

    @Override
    public void writeData(String data, String fileName) {

    }
}