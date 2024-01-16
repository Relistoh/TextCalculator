package com.github.relistoh.text_calculator.reader_writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.relistoh.text_calculator.additional_classes.Message;
import com.github.relistoh.text_calculator.expression.ExpressionFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class JsonManager implements TextFile {
    @Override
    public String readData(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(
                objectMapper.readValue(new File(fileName + ".json"), new TypeReference<List<Message>>() {})
        );
    }

    public String xmlToJson(String calculatedData) throws JsonProcessingException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(calculatedData, new TypeReference<>() {
        });

        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(dialog);
    }

    public String processedData(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(data, new TypeReference<>() {
        });

        for (Message message : dialog) {
            message.senderMessage = ExpressionFinder.evaluateMathExpressions(message.senderMessage);
        }

        return objectMapper.writeValueAsString(dialog);
    }

    public String jsonDataToString(String calculatedData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(calculatedData, new TypeReference<>() {
        });

        StringBuilder stringData = new StringBuilder();

        for (Message message : dialog) {
            stringData.append(message.toString()).append("\n");
        }

        return stringData.toString();
    }

    public String textToJson(String calculatedData) throws JsonProcessingException {
        List<Message> dialog = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (BufferedReader br = new BufferedReader(new StringReader(calculatedData))) {
            String line;
            int a = 1;
            while ((line = br.readLine()) != null) {
                dialog.add(new Message("User" + a, line));
                a++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectMapper.writeValueAsString(dialog);
    }

    @Override
    public void writeData(String data, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(data, new TypeReference<>() {
        });

        objectMapper.writeValue(new File(fileName + ".json"), dialog);
    }
}
