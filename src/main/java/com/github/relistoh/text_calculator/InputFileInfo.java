package com.github.relistoh.text_calculator;

public class InputFileInfo {
    public String fileName;
    public String fileExtencion;
    public String fileType;

    public InputFileInfo() {
    }

    @Override
    public String toString() {
        return "InputFileInfo{" +
                "fileName='" + fileName + '\'' +
                ", fileExtencion='" + fileExtencion + '\'' +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
