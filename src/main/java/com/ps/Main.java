package com.ps;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Variable to store the user's choice
        String userChoice;

        // Main application loop
        while (true) {
            // Display home screen
            System.out.println("=== Home Screen ===");
            System.out.println("1) Add Deposit");
            System.out.println("2) Make Payment (Debit)");
            System.out.println("3) View Ledger");
            System.out.println("4) Exit");
            System.out.print("Choose an option: ");

            // Read user input
            userChoice = scanner.nextLine().toUpperCase();

            // Handle user choice using a switch-case structure
            switch (userChoice) {
                case "1":
                    // Call method to handle deposit
                    addDeposit();
                    break;
                case "2":
                    // Call method to handle payment
                    makePayment();
                    break;
                case "3":
                    // Call method to display ledger options
                    viewLedger();
                    break;
                case "4":
                    // Exit the program
                    System.out.println("Exiting application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //  adding a deposit
    public static void addDeposit() {
        // Prompting the user for deposit information
        System.out.println("command for adding deposit goes here");
    }

    //  making a payment (debit)
    public static void makePayment() {
        System.out.println("command for making payment goes here");
    }

    //  viewing the ledger
    public static void viewLedger() {
        // Display ledger options (All transactions, deposits, payments)
        System.out.println("command for displaying ledger goes here.");
    }
}