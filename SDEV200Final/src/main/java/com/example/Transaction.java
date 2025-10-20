package com.example;

import java.util.Date;

public class Transaction extends Account {
    private int transactionId;
    private double amount;
    private Date date;
    private String type;

    public Transaction(int accountId, String accountType, int userId, double balance) {
        super(accountId, accountType, userId, balance);
    }

    public void makeTransaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
        this.date = new Date();
        System.out.println("Transaction of $" + amount + " (" + type + ") created.");
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}