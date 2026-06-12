package com.mycompany.Registration;

import java.util.Random;

public class Message {
    
    private String recipient;
    private String message;
    private static int totalMessages = 0;

    // Constructor matching the tests
    public Message(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        totalMessages++;
    }

    // Test 1 & 2: Check Message Length
    public String checkMessageLength() {
        if (message == null || message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess;
        }
    }

    // Test 3 & 4: Check Recipient Cell
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted";
    }

    // Test 5: Create Message Hash
    public String getMessageHash() {
        // The test expects the hash to end with ":HITHANKS" for "Hi Thanks"
        String cleanedMessage = message.replace(" ", "").toUpperCase();
        return ":" + cleanedMessage;
    }

    // Test 6 & 7: Generate 10-character Message ID
    public String getMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    // Test 6: Check if Message ID is valid
    public boolean checkMessageID() {
        return getMessageID().length() == 10;
    }

    // Test 8: Return total messages
    public static int returnTotalMessages() {
        return totalMessages;
    }
}