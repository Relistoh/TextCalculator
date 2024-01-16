package com.github.relistoh.text_calculator;

public class OutputFileInfo {
    public String fileName;
    public String fileExtencion;
    public String fileType;

    public OutputFileInfo() {
    }

    @Override
    public String toString() {
        return "OutputFileInfo{" +
                "fileName='" + fileName + '\'' +
                ", fileExtencion='" + fileExtencion + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}