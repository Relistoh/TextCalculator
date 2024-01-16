package com.github.relistoh.text_calculator;

import java.io.IOException;

public class SwitchManager {
    private TextFile textFile;
    public String calculatedData;
    public String data;

    public SwitchManager() {
    }

    public void inputSwitch(InputFileInfo inputFileInfo) throws Exception {
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
                    case "encrypted" -> {
                        textFile = new PlainTextManager();
                        TextFile encFile = new EncryptionManager(textFile);
                        ((EncryptionManager) encFile).decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".txt");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ExpressionFinder.evaluateMathExpressions(data);
                    }
                    case "archived, then encrypted" -> {
                        textFile = new PlainTextManager();
                        TextFile encFile = new EncryptionManager(textFile);
                        ((EncryptionManager) encFile).decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".txt.zip");
                        TextFile archFile = new ArchiveManager(textFile);
                        ((ArchiveManager) archFile).unzip(inputFileInfo.fileName + ".txt.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ExpressionFinder.evaluateMathExpressions(data);
                    }
                    case "encrypted, then archived" -> {
                        textFile = new PlainTextManager();
                        TextFile archFile = new ArchiveManager(textFile);
                        ((ArchiveManager) archFile).unzip(inputFileInfo.fileName + ".enc.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        TextFile encFile = new EncryptionManager(textFile);
                        ((EncryptionManager) encFile).decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".txt");
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
                    case "archived" -> {
                        textFile = new JsonManager();
                        ArchiveManager.unzip(inputFileInfo.fileName + ".json.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((JsonManager) textFile).processedData(data);
                    }
                    case "encrypted" -> {
                        textFile = new JsonManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".json");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((JsonManager) textFile).processedData(data);
                    }
                    case "Archived, then encrypted" -> {
                        textFile = new JsonManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".json.zip");
                        ArchiveManager.unzip(inputFileInfo.fileName + ".json.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((JsonManager) textFile).processedData(data);
                    }
                    case "Encrypted, then archived" -> {
                        textFile = new JsonManager();
                        ArchiveManager.unzip(inputFileInfo.fileName + ".enc.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".json");
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

    public void outputSwitch(OutputFileInfo outputFileInfo, InputFileInfo inputFileInfo) throws IOException {
        switch (inputFileInfo.fileExtencion) {
            case "plain text" -> {
                switch (outputFileInfo.fileExtencion) {
                    case "plain text" -> {
                        textFile = new PlainTextManager();
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                    case "json" -> {
                        textFile = new JsonManager();
                        calculatedData = ((JsonManager) textFile).textToJson(calculatedData);
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                    case "xml" -> {
                        textFile = new XmlManager();
                        calculatedData = ((XmlManager) textFile).textToXml(calculatedData);
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                }
            }
            case "json" -> {
                switch (outputFileInfo.fileExtencion) {
                    case "plain text" -> {
                        textFile = new JsonManager();
                        calculatedData = ((JsonManager) textFile).jsonDataToString(calculatedData);
                        textFile = new PlainTextManager();
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                    case "json" -> {
                        textFile = new JsonManager();
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                    case "xml" -> {
                        textFile = new XmlManager();
                        calculatedData = ((XmlManager) textFile).jsonToXml(calculatedData);
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                }
            }
            case "xml" -> {
                switch (outputFileInfo.fileExtencion) {
                    case "plain text" -> {
                        textFile = new XmlManager();
                        calculatedData = ((XmlManager) textFile).xmlDataToString(calculatedData);
                        textFile = new PlainTextManager();
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                    case "json" -> {
                        textFile = new JsonManager();
                        calculatedData = ((JsonManager) textFile).xmlToJson(calculatedData);
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                    case "xml" -> {
                        textFile = new XmlManager();
                        textFile.writeData(calculatedData, outputFileInfo.fileName);
                    }
                }
            }
        }

    }
}
