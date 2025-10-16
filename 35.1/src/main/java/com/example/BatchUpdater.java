// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
//  10/16/25
// Program allows users connect to a database and see the difference between a batch update and non-batch update.

package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BatchUpdater extends Application {

    private Stage primaryStage;
    private ComboBox<String> driverComboBox;
    private ComboBox<String> urlComboBox;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label statusLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Connect to DB");
        showConnectionScreen();
    }

    // Creates the connection screen shown in 34.3
    private void showConnectionScreen() {
        driverComboBox = new ComboBox<>();
        driverComboBox.getItems().addAll("com.mysql.cj.jdbc.Driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        urlComboBox = new ComboBox<>();
        urlComboBox.getItems().addAll("jdbc:sqlserver://localhost:1433");

        usernameField = new TextField();
        passwordField = new PasswordField();
        
        Button connectButton = new Button("Connect to DB");
        connectButton.setOnAction(e -> connectToDatabase());
        
        Button closeButton = new Button("Close Dialog");
        closeButton.setOnAction(e -> primaryStage.close());
        
        statusLabel = new Label("");

        // Layout stuff
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(new Label("JDBC Driver:"), 0, 0);
        grid.add(driverComboBox, 1, 0);
        grid.add(new Label("Database URL:"), 0, 1);
        grid.add(urlComboBox, 1, 1);
        grid.add(new Label("Username:"), 0, 2);
        grid.add(usernameField, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(connectButton, 1, 4);
        grid.add(closeButton, 0, 4);
        grid.add(statusLabel, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Connection function
    private void connectToDatabase() {
        String jdbcDriver = driverComboBox.getValue();
        String dbUrl = urlComboBox.getValue();
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Class.forName(jdbcDriver);
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            statusLabel.setText("Connected to " + dbUrl);
            connection.close();

            showUpdateScreen(); // Go to update screen after login
        } catch (ClassNotFoundException e) {
            statusLabel.setText("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            statusLabel.setText("Connection failed: " + e.getMessage());
        }
    }

    // Creates the update screen shown in 35.1
    private void showUpdateScreen() {
        Label updateStatusLabel = new Label("");
        Button connectButton = new Button("Connect to Database");
        connectButton.setOnAction(e -> showConnectionScreen());

        TextArea resultsArea = new TextArea();
        resultsArea.setEditable(false);
        
        Button batchUpdateButton = new Button("Batch Update");
        batchUpdateButton.setOnAction(e -> performBatchUpdate(resultsArea, updateStatusLabel));
        
        Button nonBatchUpdateButton = new Button("Non-Batch Update");
        nonBatchUpdateButton.setOnAction(e -> performNonBatchUpdate(resultsArea, updateStatusLabel));

        // Layout stuff
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(updateStatusLabel, 0, 0, 2, 1);
        grid.add(connectButton, 2, 0);
        grid.add(resultsArea, 0, 1, 3, 1);
        grid.add(batchUpdateButton, 0, 2);
        grid.add(nonBatchUpdateButton, 1, 2);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
    }

    // Method for batch update
    private void performBatchUpdate(TextArea resultsArea, Label updateStatusLabel) {
        long startTime = System.currentTimeMillis();
        try (Connection connection = DriverManager.getConnection(urlComboBox.getValue(), usernameField.getText(), passwordField.getText())) {
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Temp(num1, num2, num3) VALUES (?, ?, ?)");

            // Random number for each double value
            for (int i = 0; i < 1000; i++) {
                pstmt.setDouble(1, Math.random());
                pstmt.setDouble(2, Math.random());
                pstmt.setDouble(3, Math.random());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            connection.commit();
            long elapsedTime = System.currentTimeMillis() - startTime;
            updateStatusLabel.setText("Batch update succeeded");
            resultsArea.appendText("Batch update completed\nThe elapsed time is " + elapsedTime + " ms\n");
        } catch (SQLException e) {
            updateStatusLabel.setText("Batch update failed: " + e.getMessage());
        }
    }

    // Method for non-batch update
    private void performNonBatchUpdate(TextArea resultsArea, Label updateStatusLabel) {
        long startTime = System.currentTimeMillis();
        try (Connection connection = DriverManager.getConnection(urlComboBox.getValue(), usernameField.getText(), passwordField.getText())) {
            connection.setAutoCommit(true);
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Temp(num1, num2, num3) VALUES (?, ?, ?)");

            // Random number for each double value
            for (int i = 0; i < 1000; i++) {
                pstmt.setDouble(1, Math.random());
                pstmt.setDouble(2, Math.random());
                pstmt.setDouble(3, Math.random());
                pstmt.executeUpdate();
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            updateStatusLabel.setText("Non-batch update succeeded");
            resultsArea.appendText("Non-Batch update completed\nThe elapsed time is " + elapsedTime + " ms\n");
        } catch (SQLException e) {
            updateStatusLabel.setText("Non-batch update failed: " + e.getMessage());
        }
    }
}