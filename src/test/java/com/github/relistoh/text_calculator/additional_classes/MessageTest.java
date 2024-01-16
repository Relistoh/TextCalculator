package com.github.relistoh.text_calculator.additional_classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    void testMessageConstructor() {
        Message message = new Message("John", "Hello there");
        assertEquals("John", message.getSenderName());
        assertEquals("Hello there", message.getSenderMessage());
    }

    @Test
    void testDefaultConstructor() {
        Message message = new Message();
        assertNull(message.getSenderName());
        assertNull(message.getSenderMessage());
    }

    @Test
    void testToString() {
        Message message = new Message("Alice", "How are you?");
        assertEquals("Alice: How are you?", message.toString());
    }

    @Test
    void testSetSenderName() {
        Message message = new Message();
        message.setSenderName("Bob");
        assertEquals("Bob", message.getSenderName());
    }

    @Test
    void testSetSenderMessage() {
        Message message = new Message();
        message.setSenderMessage("This is a test");
        assertEquals("This is a test", message.getSenderMessage());
    }
}