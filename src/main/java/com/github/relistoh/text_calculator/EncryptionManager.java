package com.github.relistoh.text_calculator;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class EncryptionManager implements TextFile{

    private static final String ALGORITHM = "AES";
    private static final byte[] STATIC_KEY_BYTES = "1234567890123456".getBytes();
    private static final SecretKey secretKey = new SecretKeySpec(STATIC_KEY_BYTES, ALGORITHM);
    private final TextFile encFile;

    public EncryptionManager(TextFile encFile) {
        this.encFile = encFile;
    }

    @Override
    public String readData(String fileName) {
        return null;
    }

    @Override
    public void writeData(String fileName, String data) {

    }

    public static void encryptFile(String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(outputFile), cipher);
             FileInputStream fis = new FileInputStream(inputFile)) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void decryptFile(String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(outputFile), cipher);
             FileInputStream fis = new FileInputStream(inputFile)) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }
}
