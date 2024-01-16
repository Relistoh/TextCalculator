package com.github.relistoh.text_calculator;

import com.github.relistoh.text_calculator.additional_classes.InputFileInfo;
import com.github.relistoh.text_calculator.additional_classes.OutputFileInfo;
import com.github.relistoh.text_calculator.archivation_encryption.ArchivationManager;
import com.github.relistoh.text_calculator.archivation_encryption.EncryptionManager;
import com.github.relistoh.text_calculator.expression.ExpressionFinder;
import com.github.relistoh.text_calculator.reader_writer.JsonManager;
import com.github.relistoh.text_calculator.reader_writer.PlainTextManager;
import com.github.relistoh.text_calculator.reader_writer.TextFile;
import com.github.relistoh.text_calculator.reader_writer.XmlManager;

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
                        ArchivationManager.unzip(inputFileInfo.fileName + ".txt.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ExpressionFinder.evaluateMathExpressions(data);
                    }
                    case "encrypted" -> {
                        textFile = new PlainTextManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".txt");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ExpressionFinder.evaluateMathExpressions(data);
                    }
                    case "archived, then encrypted" -> {
                        textFile = new PlainTextManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".txt.zip");
                        ArchivationManager.unzip(inputFileInfo.fileName + ".txt.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ExpressionFinder.evaluateMathExpressions(data);
                    }
                    case "encrypted, then archived" -> {
                        textFile = new PlainTextManager();
                        ArchivationManager.unzip(inputFileInfo.fileName + ".enc.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".txt");
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
                        ArchivationManager.unzip(inputFileInfo.fileName + ".json.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((JsonManager) textFile).processedData(data);
                    }
                    case "encrypted" -> {
                        textFile = new JsonManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".json");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((JsonManager) textFile).processedData(data);
                    }
                    case "archived, then encrypted" -> {
                        textFile = new JsonManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".json.zip");
                        ArchivationManager.unzip(inputFileInfo.fileName + ".json.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((JsonManager) textFile).processedData(data);
                    }
                    case "encrypted, then archived" -> {
                        textFile = new JsonManager();
                        ArchivationManager.unzip(inputFileInfo.fileName + ".enc.zip", "/Users/anton/IdeaProjects/TextCalculator");
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
                    case "archived" -> {
                        textFile = new XmlManager();
                        ArchivationManager.unzip(inputFileInfo.fileName + ".xml.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((XmlManager) textFile).processedData(data);
                    }
                    case "encrypted" -> {
                        textFile = new XmlManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".xml");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((XmlManager) textFile).processedData(data);
                    }
                    case "archived, then encrypted" -> {
                        textFile = new XmlManager();
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".xml.zip");
                        ArchivationManager.unzip(inputFileInfo.fileName + ".xml.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((XmlManager) textFile).processedData(data);
                    }
                    case "encrypted, then archived" -> {
                        textFile = new XmlManager();
                        ArchivationManager.unzip(inputFileInfo.fileName + ".enc.zip", "/Users/anton/IdeaProjects/TextCalculator");
                        EncryptionManager.decryptFile(inputFileInfo.fileName + ".enc", inputFileInfo.fileName + ".xml");
                        data = textFile.readData(inputFileInfo.fileName);
                        calculatedData = ((XmlManager) textFile).processedData(data);
                    }
                }
            }
        }
    }

    public void outputSwitch(OutputFileInfo outputFileInfo, InputFileInfo inputFileInfo) throws Exception {
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

        switch (outputFileInfo.fileType) {
            case "archived" -> {
                if (outputFileInfo.fileExtencion.equals("plain text"))
                    ArchivationManager.archiveFile(outputFileInfo.fileName + ".txt");
                else
                    ArchivationManager.archiveFile(outputFileInfo.fileName + "." + outputFileInfo.fileExtencion);
            }
            case "encrypted" -> {
                if (outputFileInfo.fileExtencion.equals("plain text"))
                    EncryptionManager.encryptFile(outputFileInfo.fileName + ".txt", outputFileInfo.fileExtencion + ".enc");
                else
                    EncryptionManager.encryptFile(outputFileInfo.fileName + "." + outputFileInfo.fileExtencion, outputFileInfo.fileName + ".enc");
            }
            case "archived, then encrypted" -> {
                if (outputFileInfo.fileExtencion.equals("plain text")) {
                    ArchivationManager.archiveFile(outputFileInfo.fileName + ".txt");
                    EncryptionManager.encryptFile(outputFileInfo.fileName + ".txt.zip", outputFileInfo.fileName + ".enc");
                } else {
                    ArchivationManager.archiveFile(outputFileInfo.fileName + "." + outputFileInfo.fileExtencion);
                    EncryptionManager.encryptFile(outputFileInfo.fileName + "." + outputFileInfo.fileExtencion + ".zip", outputFileInfo.fileName + ".enc");
                }
            }
            case "encrypted, then archived" -> {
                if (outputFileInfo.fileExtencion.equals("plain text"))
                    EncryptionManager.encryptFile(outputFileInfo.fileName + ".txt", outputFileInfo.fileName + ".enc");
                else
                    EncryptionManager.encryptFile(outputFileInfo.fileName + "." + outputFileInfo.fileExtencion, outputFileInfo.fileExtencion + ".enc");
                ArchivationManager.archiveFile(outputFileInfo.fileName + ".enc");
            }
        }

    }
}
