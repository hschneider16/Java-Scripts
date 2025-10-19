package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Account {
    private int accountId;
    private double balance;
    private String accountType;
    private int userId;

    public Account(int accountId, String accountType, int userId) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.userId = userId;
        this.balance = 500.0; // Starts as 500 for test purposes
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

        public void saveAccountData() {
        String fileName = userId + "_" + accountType + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Account ID: " + accountId);
            writer.newLine();
            writer.write("Balance: " + balance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAccountData() {
        String fileName = userId + "_" + accountType + ".txt";

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Load account data from file
            }
        } catch (FileNotFoundException e) {
            System.out.println("Account data file not found");
            saveAccountData(); // Create new account file if not found
        }
    }
}