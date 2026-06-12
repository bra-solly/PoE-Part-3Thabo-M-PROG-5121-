/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Registration;

/**
 *
 * @author Administrator
 */


import java.util.Scanner;

public class MessageApplication {

    // ==========================================
    // PART 1: REGISTRATION VARIABLES
    // ==========================================
    private static String storedUsername = "";
    private static String storedPassword = "";
    private static String storedContact = "";
    private static boolean isRegistered = false;
    private static boolean isLoggedIn = false;

    // ==========================================
    // PART 2: MESSAGE VARIABLES
    // ==========================================
    private static int messageIDCounter = 0;
    private static int totalMessageCount = 0;

    // ==========================================
    // PART 3: TEST DATA (5 messages)
    // ==========================================
    static String[] testDataRecipients = {
        "+27834557896",
        "+27834789654",
        "+27839876543",
        "+27831234567",
        "+27836543210"
    };

    static String[] testDataMessages = {
        "Did you get the cake?",
        "Where are you? I have been waiting for 2 hours.",
        "On my way!",
        "Please call me back.",
        "Are you coming to the party?"
    };

    static String[] testDataFlags = {"Sent", "Stored", "Disregard", "Stored", "Sent"};

    // ==========================================
    // PART 3: THE 5 REQUIRED ARRAYS
    // ==========================================
    static String[] sentMessages = new String[10];
    static String[] disregardedMessages = new String[10];
    static String[] storedMessages = new String[10];
    static int[] messageHash = new int[10];
    static int[] messageID = new int[10];

    // Extra arrays for full search/report functionality
    static String[] sentRecipients = new String[10];
    static String[] allRecipients = new String[10];
    static String[] allMessages = new String[10];
    static String[] allFlags = new String[10];

    static int sentCount = 0;
    static int disregardedCount = 0;
    static int storedCount = 0;
    static int totalMessages = 0;
    static final int MAX_MESSAGE_LENGTH = 250;

    static Scanner scanner = new Scanner(System.in);

    // ==========================================
    // MAIN METHOD
    // ==========================================
    public static void main(String[] args) {
        processMessages();

        int choice;
        do {
            displayMainMenu();
            choice = getValidInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    sendMessage();
                    break;
                case 4:
                    storedMessagesMenu();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // ==========================================
    // MAIN MENU (Part 1 + Part 3)
    // ==========================================
    public static void displayMainMenu() {
        System.out.println("\n=================================");
        System.out.println("        QUICK CHAT MENU");
        System.out.println("=================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Send Message");
        System.out.println("4. Stored Messages");
        System.out.println("5. Exit");
        System.out.println("=================================");
    }

    // ==========================================
    // PART 1: CHECK USERNAME
    // Must contain "_" and be no more than 5 chars
    // ==========================================
    public static String checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (username.contains("_") && username.length() <= 5) {
            return "Username successfully captured.";
        }
        return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
    }

    // ==========================================
    // PART 1: CHECK PHONE NUMBER
    // Must start with +27 and be exactly 12 chars
    // ==========================================
    public static String checkPhoneNumber(String phone) {
        if (phone != null && phone.startsWith("+27") && phone.length() == 12) {
            return "Cell phone number successfully added.";
        }
        return "Cell phone number is incorrectly formatted or does not contain South African area code.";
    }

    // ==========================================
    // PART 1: VALIDATE PASSWORD
    // At least 8 chars, uppercase, lowercase,
    // number, and special character
    // ==========================================
    public static String validatePassword(String password) {
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else hasSpecial = true;
        }

        if (password.length() >= 8 && hasUpper && hasLower && hasNumber && hasSpecial) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a lowercase letter, a number and a special character.";
    }

    // ==========================================
    // PART 1: REGISTER (signup)
    // ==========================================
    public static void register() {
        if (isRegistered) {
            System.out.println("You are already registered. Please login.");
            return;
        }

        System.out.println("\n--- REGISTRATION ---");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        String usernameResult = checkUsername(username);
        System.out.println(usernameResult);
        if (!usernameResult.equals("Username successfully captured.")) {
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        String passwordResult = validatePassword(password);
        System.out.println(passwordResult);
        if (!passwordResult.equals("Password successfully captured.")) {
            return;
        }

        System.out.print("Enter phone number (+27...): ");
        String phone = scanner.nextLine();
        String phoneResult = checkPhoneNumber(phone);
        System.out.println(phoneResult);
        if (!phoneResult.equals("Cell phone number successfully added.")) {
            return;
        }

        storedUsername = username;
        storedPassword = password;
        storedContact = phone;
        isRegistered = true;
        System.out.println("Your Registration successful!");
    }

    // ==========================================
    // PART 1: LOGIN
    // ==========================================
    public static void login() {
        if (!isRegistered) {
            System.out.println("Please register first.");
            return;
        }
        if (isLoggedIn) {
            System.out.println("You are already logged in as " + storedUsername + ".");
            return;
        }

        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            isLoggedIn = true;
            System.out.println("Login successful! Welcome, " + username + ".");
        } else {
            System.out.println("Login failed: Invalid username or password.");
        }
    }

    // ==========================================
    // PART 2: GENERATE MESSAGE ID
    // ==========================================
    public static int generateMessageID() {
        messageIDCounter++;
        return messageIDCounter;
    }

    // ==========================================
    // PART 2: CHECK MESSAGE ID
    // ==========================================
    public static boolean checkMessageID(int id) {
        return id > 0;
    }

    // ==========================================
    // PART 2: CHECK RECIPIENT CELL
    // Returns String (not boolean)
    // ==========================================
    public static String checkRecipientCell(String recipient) {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number success";
        }
        return "Cell phone number is incorrectly formatted";
    }

    // ==========================================
    // PART 2: CHECK MESSAGE LENGTH
    // Max 250 characters
    // ==========================================
    public static boolean checkMessageLength(String message) {
        return message != null && message.length() <= MAX_MESSAGE_LENGTH;
    }

    // ==========================================
    // PART 2: CREATE MESSAGE HASH
    // ==========================================
    public static int createMessageHash(String message) {
        if (message == null) return 0;
        return message.hashCode();
    }

    // ==========================================
    // PART 2: CREATE MESSAGE
    // Returns formatted message string
    // ==========================================
    public static String createMessage(int id, String recipient, String message) {
        return "Message created successfully!"
                + "\nMessage ID: " + id
                + "\nRecipient:  " + recipient
                + "\nMessage:    " + message;
    }

    // ==========================================
    // PART 2: PRINT MESSAGES (StringBuilder)
    // ==========================================
    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ALL MESSAGES ===\n");
        for (int i = 0; i < totalMessages; i++) {
            sb.append("Message ID: ").append(messageID[i]).append("\n");
            sb.append("Recipient:  ").append(allRecipients[i]).append("\n");
            sb.append("Message:    ").append(allMessages[i]).append("\n");
            sb.append("Hash:       ").append(messageHash[i]).append("\n");
            sb.append("Flag:       ").append(allFlags[i]).append("\n");
            if (i < totalMessages - 1) {
                sb.append("--------------------\n");
            }
        }
        return sb.toString();
    }

    // ==========================================
    // PART 2: RETURN TOTAL MESSAGES
    // ==========================================
    public static int returnTotalMessages() {
        return totalMessageCount;
    }

    // ==========================================
    // PART 2: SEND MESSAGE (SentMessage)
    // With all Part 2 validations
    // ==========================================
    public static void sendMessage() {
        if (!isLoggedIn) {
            System.out.println("Please login first to send messages.");
            return;
        }

        System.out.println("\n--- SEND MESSAGE ---");

        // Validate recipient using Part 2 checkRecipientCell
        System.out.print("Enter recipient phone number (+27...): ");
        String recipient = scanner.nextLine();
        String cellResult = checkRecipientCell(recipient);
        if (!cellResult.equals("Cell phone number success")) {
            System.out.println(cellResult);
            return;
        }

        // Validate message length using Part 2 checkMessageLength
        System.out.print("Enter message (max " + MAX_MESSAGE_LENGTH + " chars): ");
        String message = scanner.nextLine();
        if (!checkMessageLength(message)) {
            System.out.println("Error: Message exceeds " + MAX_MESSAGE_LENGTH + " characters.");
            return;
        }

        // Generate and validate message ID using Part 2 methods
        int id = generateMessageID();
        if (!checkMessageID(id)) {
            System.out.println("Error: Invalid message ID generated.");
            return;
        }

        // Create message hash using Part 2 createMessageHash
        int hash = createMessageHash(message);

        // Display created message using Part 2 createMessage
        System.out.println("\n" + createMessage(id, recipient, message));
        System.out.println("Hash:      " + hash);

        // Part 3: Store in sentMessages array
        if (sentCount < sentMessages.length) {
            sentMessages[sentCount] = message;
            sentRecipients[sentCount] = recipient;
            sentCount++;
        }

        // Part 3: Store in tracking arrays for search/report
        if (totalMessages < allMessages.length) {
            messageID[totalMessages] = id;
            messageHash[totalMessages] = hash;
            allRecipients[totalMessages] = recipient;
            allMessages[totalMessages] = message;
            allFlags[totalMessages] = "Sent";
            totalMessages++;
            totalMessageCount++;
        }

        System.out.println("\nMessage successfully sent.");
    }

    // ==========================================
    // PART 3: PROCESS TEST DATA INTO ARRAYS
    // ==========================================
    public static void processMessages() {
        for (int i = 0; i < 5; i++) {
            messageID[totalMessages] = i + 1;
            messageIDCounter = i + 1;

            messageHash[totalMessages] = createMessageHash(testDataMessages[i]);

            allRecipients[totalMessages] = testDataRecipients[i];
            allMessages[totalMessages] = testDataMessages[i];
            allFlags[totalMessages] = testDataFlags[i];

            switch (testDataFlags[i]) {
                case "Sent":
                    sentMessages[sentCount] = testDataMessages[i];
                    sentRecipients[sentCount] = testDataRecipients[i];
                    sentCount++;
                    break;
                case "Stored":
                    storedMessages[storedCount] = testDataMessages[i];
                    storedCount++;
                    break;
                case "Disregard":
                    disregardedMessages[disregardedCount] = testDataMessages[i];
                    disregardedCount++;
                    break;
            }

            totalMessages++;
            totalMessageCount++;
        }

        System.out.println("Messages processed successfully!");
        System.out.println("  Sent: " + sentCount + " | Stored: " + storedCount
                + " | Disregarded: " + disregardedCount);
    }

    // ==========================================
    // PART 3: STORED MESSAGES MENU (6 OPTIONS)
    // ==========================================
    public static void storedMessagesMenu() {
        int choice;
        do {
            System.out.println("\n===============================");
            System.out.println("   STORED MESSAGES MENU");
            System.out.println("===============================");
            System.out.println("1. Display sent recipients");
            System.out.println("2. Search by message ID");
            System.out.println("3. Search by message hash");
            System.out.println("4. Display longest message");
            System.out.println("5. Display message report");
            System.out.println("6. Return to main menu");
            System.out.println("===============================");

            choice = getValidInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    displaySentRecipients();
                    break;
                case 2:
                    searchByMessageID();
                    break;
                case 3:
                    searchByMessageHash();
                    break;
                case 4:
                    displayLongestMessage();
                    break;
                case 5:
                    displayMessageReport();
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    // ==========================================
    // PART 3: OPTION 1 - DISPLAY SENT RECIPIENTS
    // ==========================================
    public static void displaySentRecipients() {
        System.out.println("\n--- Sent Recipients ---");
        if (sentCount == 0) {
            System.out.println("No sent messages found.");
            return;
        }
        for (int i = 0; i < sentCount; i++) {
            System.out.println((i + 1) + ". " + sentRecipients[i]
                    + " - \"" + sentMessages[i] + "\"");
        }
    }

    // ==========================================
    // PART 3: OPTION 2 - SEARCH BY MESSAGE ID
    // ==========================================
    public static void searchByMessageID() {
        int searchID = getValidInt("Enter Message ID to search: ");
        boolean found = false;

        for (int i = 0; i < totalMessages; i++) {
            if (messageID[i] == searchID) {
                System.out.println("\nMessage found!");
                System.out.println("ID:        " + messageID[i]);
                System.out.println("Recipient: " + allRecipients[i]);
                System.out.println("Message:   " + allMessages[i]);
                System.out.println("Flag:      " + allFlags[i]);
                System.out.println("Hash:      " + messageHash[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Message with ID " + searchID + " not found.");
        }
    }

    // ==========================================
    // PART 3: OPTION 3 - SEARCH BY MESSAGE HASH
    // ==========================================
    public static void searchByMessageHash() {
        System.out.print("Enter Message Hash to search: ");
        int searchHash;
        try {
            searchHash = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid hash value.");
            return;
        }

        boolean found = false;
        for (int i = 0; i < totalMessages; i++) {
            if (messageHash[i] == searchHash) {
                System.out.println("\nMessage found!");
                System.out.println("ID:        " + messageID[i]);
                System.out.println("Recipient: " + allRecipients[i]);
                System.out.println("Message:   " + allMessages[i]);
                System.out.println("Flag:      " + allFlags[i]);
                System.out.println("Hash:      " + messageHash[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Message with hash " + searchHash + " not found.");
        }
    }

    // ==========================================
    // PART 3: OPTION 4 - DISPLAY LONGEST MESSAGE
    // ==========================================
    public static void displayLongestMessage() {
        if (totalMessages == 0) {
            System.out.println("No messages to display.");
            return;
        }

        String longest = "";
        int longestIndex = 0;

        for (int i = 0; i < totalMessages; i++) {
            if (allMessages[i].length() > longest.length()) {
                longest = allMessages[i];
                longestIndex = i;
            }
        }

        System.out.println("\n--- Longest Message ---");
        System.out.println("Message: " + longest);
        System.out.println("Length:  " + longest.length() + " characters");
        System.out.println("ID:      " + messageID[longestIndex]);
        System.out.println("Flag:    " + allFlags[longestIndex]);
    }

    // ==========================================
    // PART 3: OPTION 5 - DISPLAY MESSAGE REPORT
    // ==========================================
    public static void displayMessageReport() {
        System.out.println("\n========================================");
        System.out.println("          MESSAGE REPORT");
        System.out.println("========================================");

        System.out.println("\n--- Sent Messages (" + sentCount + ") ---");
        for (int i = 0; i < sentCount; i++) {
            System.out.println("  " + (i + 1) + ". " + sentMessages[i]);
        }

        System.out.println("\n--- Stored Messages (" + storedCount + ") ---");
        for (int i = 0; i < storedCount; i++) {
            System.out.println("  " + (i + 1) + ". " + storedMessages[i]);
        }

        System.out.println("\n--- Disregarded Messages (" + disregardedCount + ") ---");
        for (int i = 0; i < disregardedCount; i++) {
            System.out.println("  " + (i + 1) + ". " + disregardedMessages[i]);
        }

        System.out.println("\n--- Full Message Details ---");
        for (int i = 0; i < totalMessages; i++) {
            System.out.println("Message " + messageID[i] + ":");
            System.out.println("  Recipient : " + allRecipients[i]);
            System.out.println("  Message   : " + allMessages[i]);
            System.out.println("  Flag      : " + allFlags[i]);
            System.out.println("  Hash      : " + messageHash[i]);
            if (i < totalMessages - 1) {
                System.out.println("  ----------------------------");
            }
        }

        System.out.println("========================================");
        System.out.println("Total: " + sentCount + " sent, " + storedCount
                + " stored, " + disregardedCount + " disregarded");
    }

    // ==========================================
    // HELPER: SAFE INTEGER INPUT
    // ==========================================
    public static int getValidInt(String prompt) {
        int value;
        while (true) {
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}