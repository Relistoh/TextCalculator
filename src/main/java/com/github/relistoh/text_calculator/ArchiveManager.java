package com.github.relistoh.text_calculator;

import java.io.*;
import java.util.zip.*;

public class ArchiveManager implements TextFile {

    private TextFile archFile;

    public ArchiveManager(TextFile text) {
        this.archFile = text;
    }

    @Override
    public String readData(String fileName) {
        return null;
    }

    @Override
    public void writeData(String fileName, String data) {

    }

    public static void archiveFile(String fileName) throws IOException {
        File fileToArchive = new File(fileName);
        if (!fileToArchive.exists()) {
            throw new FileNotFoundException("File not found: " + fileName);
        }

        try (FileOutputStream fos = new FileOutputStream(fileName + ".zip");
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            zipFile(fileToArchive.getAbsolutePath(), fileToArchive.getName(), zipOut);
        }
    }

    private static void zipFile(String filePath, String fileName, ZipOutputStream zipOut) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();

            while (entry != null) {
                String filePath = destDirectory + File.separator + entry.getName();
                if (!entry.isDirectory() && !entry.getName().startsWith("__MACOSX")) {
                    extractFile(zipIn, filePath);
                } else if (entry.isDirectory() && !entry.getName().startsWith("__MACOSX")) {
                    File dir = new File(filePath);
                    dir.mkdir();
                }

                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = zipIn.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
