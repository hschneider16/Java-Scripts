package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts (user_id, account_type, balance) VALUES (?, ?, ?)")) {
        statement.setInt(1, userId);
        statement.setString(2, accountType);
        statement.setDouble(3, balance);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void loadAccountData() {
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE user_id = ? AND account_type = ?")) {
        statement.setInt(1, userId);
        statement.setString(2, accountType);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                double balance = resultSet.getDouble("balance");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}