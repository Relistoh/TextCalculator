package com.github.relistoh.text_calculator;

import java.io.IOException;

public class SwitchManager {
    private TextFile textFile;
    public String calculatedData;
    public String data;

    public SwitchManager() {
    }

    public void inputSwitch(InputFileInfo inputFileInfo) throws IOException {
        switch (inputFileInfo.fileExtencion) {
            case "plain text" -> {
                switch (inputFileInfo.fileType) {
                    case "nothing" -> {
                        textFile = new PlainTextManager();
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ExpressionFinder.evaluateMathExpressions(data);
                    }
                    case "archived" -> {
                        textFile = new PlainTextManager();
                        TextFile archFile = new ArchiveManager(textFile);
                        ((ArchiveManager) archFile).unzip(inputFileInfo.fileName + ".txt.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ExpressionFinder.evaluateMathExpressions(data);
                    }
                }
            }
            case "json" -> {
                switch (inputFileInfo.fileType) {
                    case "nothing" -> {
                        textFile = new JsonManager();
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((JsonManager) textFile).processedData(data);
                    }
                }
            }
            case "xml" -> {
                switch (inputFileInfo.fileType) {
                    case "nothing" -> {
                        textFile = new XmlManager();
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((XmlManager) textFile).processedData(data);
                    }
                }
            }
        }
    }
}
