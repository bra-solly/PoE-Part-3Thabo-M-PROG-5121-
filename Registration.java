package com.mycompany.Registration;

import java.util.Scanner;

public class Registration {

    private String storedUsername;
    private String storedPassword;
    private String storedContact;

    // Username validation
    public boolean validateUser(String username) {
        return username.contains("_") && username.length() >= 8;
    }

    // Cell number validation
    public boolean validateCellNum(String contactNumber) {
        if (!contactNumber.startsWith("+27")) return false;
        if (contactNumber.length() != 12) return false;

        for (int i = 3; i < contactNumber.length(); i++) {
            if (!Character.isDigit(contactNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Password validation
    public boolean validatePassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char HP = password.charAt(i);

            if (Character.isUpperCase(HP)) hasUpper = true;
            else if (Character.isDigit(HP)) hasNumber = true;
            else if (!Character.isLetterOrDigit(HP)) hasSpecial = true;
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Signup user
    public void signup(Scanner Dt) {
        System.out.println("---- Sign Up ----");

        System.out.print("Enter username here: ");
        String username = Dt.next();

        if (!validateUser(username)) {
            System.out.println("Invalid username Entered!");
            return;
        }

        System.out.print("Enter password here: ");
        String password = Dt.next();

        if (!validatePassword(password)) {
            System.out.println("Invalid Password Entered!");
            return;
        }

        System.out.print("Enter phone (+27): ");
        String contact = Dt.next();

        if (!validateCellNum(contact)) {
            System.out.println("Invalid Phone Number Entered!");
            return;
        }

        storedUsername = username;
        storedPassword = password;
        storedContact = contact;

        System.out.println("Your Registration successful!");
    }

    // Login - returns true if successful, false otherwise
    public boolean login(Scanner Dt) {
        System.out.println("---- Login ----");

        System.out.print("Enter username: ");
        String username = Dt.next();

        System.out.print("Enter password: ");
        String password = Dt.next();

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            System.out.println("Welcome to QuickChat.");
            return true;
        } else {
            System.out.println("Login unsuccessful!!");
            return false;
        }
    }

    public String getStoredContact() {
        return storedContact;
    }
}