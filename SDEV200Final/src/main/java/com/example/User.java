package com.example;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public User(int userId, String username, String password, String email, String phoneNumber) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
    return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}