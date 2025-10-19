package com.example;

import java.util.Date;

public class Transaction extends Account {
    private int transactionId;
    private double amount;
    private Date date;
    private String type;

    public Transaction(int accountId, String accountType, int userId) {
        super(accountId, accountType, userId);
    }

    public void makeTransaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
        System.out.println("Transaction of $" + amount + " completed successfully.");
    }
}