package com.mycompany.Registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+2771869300", "Hi Mike can you join us for dinner tonight");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "A".repeat(260);
        Message msg = new Message("+2771869300", longMsg);
        assertTrue(msg.checkMessageLength().contains("Message exceeds 250 characters by 10"));
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message("+2771869300", "Hi Mike can you join us for dinner tonight");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message("08575975889", "Hi Keegan did you receive the payment");
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message("+2771869300", "Hi Thanks");
        String hash = msg.getMessageHash();
        assertTrue(hash.endsWith(":HITHANKS"));
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message("+2771869300", "Hi Mike can you join us for dinner tonight");
        System.out.println("Message ID generated: " + msg.getMessageID());
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }

    @Test
    public void testReturnTotalMessages() {
        int total = Message.returnTotalMessages();
        assertTrue(total >= 0);
    }
}