package com.github.relistoh.text_calculator;

public class Message {
    public String senderName;
    public String senderMessage;

    public Message(String senderName, String senderMessage) {
        this.senderName = senderName;
        this.senderMessage = senderMessage;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return senderName + ": " + senderMessage;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }
}
