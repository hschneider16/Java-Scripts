// Hunter Schneider
// SDEV200
// Prof. Brian Parrott
//  10/11/25
// Program allows users to view, insert, and update staff records stored in the StaffDB database.

package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class StaffDatabase extends Application {

    private Connection connection;
    private Label messageLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ExtraExercise34_01");

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);

        messageLabel = new Label(); // For displaying 'record not found' as shown in the example
        grid.add(messageLabel, 0, 0, 6, 1);

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label miLabel = new Label("MI:");
        TextField miField = new TextField();
        miField.setPrefWidth(30);

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();

        Label stateLabel = new Label("State:");
        TextField stateField = new TextField();

        Label telephoneLabel = new Label("Telephone:");
        TextField telephoneField = new TextField();

        // Buttons
        Button viewButton = new Button("View");
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");

        // Button functions
        viewButton.setOnAction(e -> viewRecord(idField.getText(), lastNameField, firstNameField, miField, addressField, cityField, stateField, telephoneField));
        insertButton.setOnAction(e -> insertRecord(idField.getText(), lastNameField.getText(), firstNameField.getText(), miField.getText(), addressField.getText(), cityField.getText(), stateField.getText(), telephoneField.getText()));
        updateButton.setOnAction(e -> updateRecord(idField.getText(), lastNameField.getText(), firstNameField.getText(), miField.getText(), addressField.getText(), cityField.getText(), stateField.getText(), telephoneField.getText()));
        clearButton.setOnAction(e -> clearFields(idField, lastNameField, firstNameField, miField, addressField, cityField, stateField, telephoneField));

        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);
        grid.add(lastNameLabel, 0, 2);
        grid.add(lastNameField, 1, 2);
        grid.add(firstNameLabel, 2, 2);
        grid.add(firstNameField, 3, 2);
        grid.add(miLabel, 4, 2);
        grid.add(miField, 5, 2);
        grid.add(addressLabel, 0, 3);
        grid.add(addressField, 1, 3);
        grid.add(cityLabel, 0, 4);
        grid.add(cityField, 1, 4);
        grid.add(stateLabel, 2, 4);
        grid.add(stateField, 3, 4);
        grid.add(telephoneLabel, 0, 5);
        grid.add(telephoneField, 1, 5);

        HBox buttonBox = new HBox(10, viewButton, insertButton, updateButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER);

        grid.add(buttonBox, 0, 6, 6, 1);

        Scene scene = new Scene(grid, 500, 210);
        primaryStage.setScene(scene);
        primaryStage.show();

        connectToDatabase();
    }

    // Connect to the database
    private void connectToDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        // Connection string
            String url = "jdbc:sqlserver://localhost:1433;user=SA;password=wingleBingle1320#;";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected!");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found");
            e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Function for viewing staff record
    private void viewRecord(String id, TextField lastNameField, TextField firstNameField, TextField miField, TextField addressField, TextField cityField, TextField stateField, TextField telephoneField) {
        String sql = "SELECT * FROM Staff WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lastNameField.setText(rs.getString("lastName"));
                firstNameField.setText(rs.getString("firstName"));
                miField.setText(rs.getString("mi"));
                addressField.setText(rs.getString("address"));
                cityField.setText(rs.getString("city"));
                stateField.setText(rs.getString("state"));
                telephoneField.setText(rs.getString("telephone"));
                messageLabel.setText("");
            } else {
                // Inform user the record wasn't found
                messageLabel.setText("Record not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function for inserting a record into the table
    private void insertRecord(String id, String lastName, String firstName, String mi, String address, String city, String state, String telephone) {
        String sql = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, lastName);
            pstmt.setString(3, firstName);
            pstmt.setString(4, mi);
            pstmt.setString(5, address);
            pstmt.setString(6, city);
            pstmt.setString(7, state);
            pstmt.setString(8, telephone);
            pstmt.executeUpdate();
            messageLabel.setText("Record inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function for updating a record in the table
    private void updateRecord(String id, String lastName, String firstName, String mi, String address, String city, String state, String telephone) {
        String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, lastName);
            pstmt.setString(2, firstName);
            pstmt.setString(3, mi);
            pstmt.setString(4, address);
            pstmt.setString(5, city);
            pstmt.setString(6, state);
            pstmt.setString(7, telephone);
            pstmt.setString(8, id);
            pstmt.executeUpdate();
            messageLabel.setText("Record updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function for clearing the text fields
    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
        messageLabel.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}