package com.mycompany.Registration;

import java.util.Scanner;

public class Logindetails {

    public static void main(String[] args) {

        Registration user = new Registration();
        Scanner Dt = new Scanner(System.in);

        int option;

        do {
            System.out.println("\n---- Main Menu ----");
            System.out.println("1. Register");
            System.out.println("2. Login with details");
            System.out.println("3. Exit program");
            System.out.print("Select option: ");

            option = Dt.nextInt();
            Dt.nextLine();

            switch (option) {
                case 1:
                    user.signup(Dt);
                    break;

                case 2:
                    boolean loggedIn = user.login(Dt);
                    if (loggedIn) {
                        quickChatMenu(Dt);
                    }
                    break;

                case 3:
                    System.out.println("Good bye");
                    break;

                default:
                    System.out.println("Incorrect option");
            }

        } while (option != 3);

        Dt.close();
    }

    private static void quickChatMenu(Scanner Dt) {
        System.out.print("How many messages do you want to send? ");
        int maxMessages = Dt.nextInt();
        Dt.nextLine();

        int messagesSentThisSession = 0;
        int choice;

        do {
            System.out.println("\n---- QuickChat ----");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Select option: ");

            choice = Dt.nextInt();
            Dt.nextLine();

            switch (choice) {
                case 1:
                    if (messagesSentThisSession >= maxMessages) {
                        System.out.println("You have reached your message limit of " + maxMessages);
                        break;
                    }

                    System.out.print("Enter recipient cell number: ");
                    String recipient = Dt.nextLine();

                    System.out.print("Enter message: ");
                    String messageText = Dt.nextLine();

                    Message msg = new Message(recipient, messageText);

                    System.out.println(msg.checkRecipientCell());
                    System.out.println(msg.checkMessageLength());

                    boolean cellValid = msg.checkRecipientCell().equals("Cell phone number successfully captured.");
                    boolean lengthValid = msg.checkMessageLength().equals("Message ready to send.");

                    if (cellValid && lengthValid) {
                        System.out.println("Message ID generated: " + msg.getMessageID());
                        String result = msg.SentMessage(Dt);
                        System.out.println(result);
                        if (result.equals("Message successfully sent.")) {
                            messagesSentThisSession++;
                            System.out.println("\n-- Message Details --");
                            System.out.println("Message ID: " + msg.getMessageID());
                            System.out.println("Message Hash: " + msg.getMessageHash());
                            System.out.println("Recipient: " + msg.getRecipient());
                            System.out.println("Message: " + msg.getMessageText());
                        }
                    } else {
                        System.out.println("Message cannot be sent due to validation errors.");
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon");
                    break;

                case 3:
                    System.out.println("\n--- All Sent Messages ---");
                    System.out.println(Message.printMessages());
                    System.out.println("Total messages sent: " + Message.returnTotalMessages());
                    System.out.println("Exiting QuickChat...");
                    break;

                default:
                    System.out.println("Incorrect option");
            }

        } while (choice != 3);
    }
}
