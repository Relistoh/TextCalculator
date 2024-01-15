package com.github.relistoh.text_calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonManager implements TextFile {
    @Override
    public String readData(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Просто читаем данные из файла и возвращаем их как строку
        return objectMapper.writeValueAsString(
                objectMapper.readValue(new File(fileName + ".json"), new TypeReference<List<Message>>() {})
        );
    }

    public String processedData(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Прямо десериализуем строку в список и обрабатываем его
        List<Message> dialog = objectMapper.readValue(data, new TypeReference<List<Message>>() {});

        for (Message message : dialog) {
            message.senderMessage = ExpressionFinder.evaluateMathExpressions(message.senderMessage);
        }

        // Возвращаем обработанный список как JSON-строку
        return objectMapper.writeValueAsString(dialog);
    }

    @Override
    public void writeData(String data, String fileName) {

    }
}
