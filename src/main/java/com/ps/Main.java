package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // scanner for user input
    static Scanner scanner = new Scanner(System.in);
    static List<Transaction> transactions = new ArrayList<>(); //store transactions

    public static void main(String[] args) {

        // Variable to store the user's choice
        String userChoice;

        // Main menu loop
        while (true) {
            // Display home screen
            System.out.println(" Home Screen ");
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
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        System.out.print("Enter a description: ");
        String description = scanner.nextLine();
        System.out.print("Enter the vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        Transaction deposit = new Transaction(currentDate, currentTime, description, vendor, amount);
        transactions.add(deposit);
        saveTransaction(deposit);
        System.out.println("Deposit added successfully.");

    }

    //  making a payment (debit)
    public static void makePayment() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();

        System.out.print("Description for payment: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Payment Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -amount; // Record payment as a negative amount

        Transaction payment = new Transaction(currentDate, currentTime, description, vendor, amount);
        transactions.add(payment);
        saveTransaction(payment);
        System.out.println("Payment recorded successfully.");
    }

    // Save transaction to file
    public static void saveTransaction(Transaction transaction) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            buffer.write(String.format("%s|%s|%s|%s|%.2f%n",
                    transaction.getDate(), transaction.getTime(),
                    transaction.getDescription(), transaction.getVendor(),
                    transaction.getAmount()));
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
//end of main menu

    //  viewing the ledger (Submenu)
    public static void viewLedger() {
        // Display ledger options (All transactions, deposits, payments)
        int ledgerChoice;

        do {
            System.out.println("1) View All Transactions");
            System.out.println("2) View Deposits");
            System.out.println("3) View Payments");
            System.out.println("0) Return to Main Menu");
            System.out.print("Choose an option: ");

            ledgerChoice = Integer.parseInt(scanner.nextLine());

            switch (ledgerChoice) {
                case 1:
                    displayAllTransactions();
                    break;
                case 2:
                    displayDeposits();
                    break;
                case 3:
                    displayPayments();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (ledgerChoice != 0);
    }


private static void displayPayments() {
    System.out.println("Payments:");
    for (Transaction transaction : transactions) {
        if (transaction.getAmount() < 0) System.out.println(transaction);
    }
    }

    private static void displayDeposits () {
        System.out.println("Deposits:");
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    private static void displayAllTransactions() {
        System.out.println("Date | Time | Description | Vendor | Amount");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    //adding a report menu
    public static void viewReports() {
        int reportChoice;

        do {
            System.out.println("Reports Menu:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger");
            System.out.print("Choose an option: ");

            reportChoice = Integer.parseInt(scanner.nextLine());

            switch (reportChoice) {
                case 1:
                    displayMonthToDate();
                    break;
                case 2:
                    displayPreviousMonth();
                    break;
                case 3:
                    displayYearToDate();
                    break;
                case 4:
                    displayPreviousYear();
                    break;
                case 5:
                    searchByVendor();
                    break;
                case 0:
                    System.out.println("Returning to Ledger...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (reportChoice != 0);
    }
    public static void displayMonthToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = currentDate.withDayOfMonth(1);

        System.out.println("This month's transactions:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("-------------------------------------------");

//        for (Transaction transaction : transactions) {
//            // Check if the transaction date is within the current month
//            if (!transaction.getDate().isBefore(firstDayOfMonth) && !transaction.getDate().isAfter(currentDate)) {
//                System.out.println(transaction);
//            }
//        }
//        System.out.println("-------------------------------------------");

    }

    public static void displayPreviousMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);
        LocalDate lastDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(currentDate.minusMonths(1).lengthOfMonth());

        System.out.println("Transactions for the previous month:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("-------------------------------------------");

//        for (Transaction transaction : transactions) {
//            // Check if the transaction date is within the previous month
//            if (!transaction.getDate().isBefore(firstDayOfPreviousMonth) && !transaction.getDate().isAfter(lastDayOfPreviousMonth)) {
//                System.out.println(transaction);
//            }
//        }
//        System.out.println("-------------------------------------------");


    }

    public static void displayYearToDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfYear = currentDate.withDayOfYear(1);

        System.out.println("Transactions for the current year:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("-------------------------------------------");

//        for (Transaction transaction : transactions) {
//            // Check if the transaction date is within the current year
//            if (!transaction.getDate().isBefore(firstDayOfYear) && !transaction.getDate().isAfter(currentDate)) {
//                System.out.println(transaction);
//            }
//        }
//        System.out.println("-------------------------------------------");

    }

    public static void displayPreviousYear() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfPreviousYear = currentDate.minusYears(1).withDayOfYear(1);
        LocalDate lastDayOfPreviousYear = currentDate.minusYears(1).withDayOfYear(currentDate.minusYears(1).lengthOfYear());

        System.out.println("Transactions for the previous year:");
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("-------------------------------------------");

//        for (Transaction transaction : transactions) {
//            // Check if the transaction date is within the previous year
//            if (!transaction.getDate().isBefore(firstDayOfPreviousYear) && !transaction.getDate().isAfter(lastDayOfPreviousYear)) {
//                System.out.println(transaction);
//            }
//        }
//        System.out.println("-------------------------------------------");

    }

    public static void searchByVendor() {
        System.out.print("Enter the vendor name: ");
        String vendorName = scanner.nextLine();

        System.out.println("Transactions for vendor: " + vendorName);
        System.out.println("Date | Time | Description | Vendor | Amount");
        System.out.println("-------------------------------------------");

        for (Transaction transaction : transactions) {
            // Check if the transaction's vendor matches the input (case-insensitive)
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(transaction);
            }
        }
        System.out.println("-------------------------------------------");

    }

}