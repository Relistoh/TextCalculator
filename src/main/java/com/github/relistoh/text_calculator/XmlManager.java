package com.github.relistoh.text_calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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

    public String xmlDataToString(String calculatedData) throws JsonProcessingException {
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(calculatedData, new TypeReference<List<Message>>() {});

        StringBuffer stringData = new StringBuffer("");

        for (Message message : dialog) {
            stringData.append(message.toString()).append("\n");
        }

        return stringData.toString();
    }

    public String jsonToXml(String calculatedData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(calculatedData, new TypeReference<List<Message>>() {});

        objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(dialog);
    }

    public String textToXml(String calculatedData) throws JsonProcessingException {
        List<Message> dialog = new ArrayList<>();
        ObjectMapper objectMapper = new XmlMapper();
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
        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        List<Message> dialog = objectMapper.readValue(data, new TypeReference<List<Message>>() {});

        objectMapper.writeValue(new File(fileName + ".xml"), dialog);
    }
}
