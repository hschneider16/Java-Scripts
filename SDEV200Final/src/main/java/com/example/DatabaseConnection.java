package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433";
        String user = "SA";
        String password = "wingleBingle1320#";
        return DriverManager.getConnection(url, user, password);
    }
}