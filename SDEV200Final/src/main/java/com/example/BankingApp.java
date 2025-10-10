package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BankingApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLoginScreen();
    }

    // LOGIN SCREEN
    private void showLoginScreen() {
        BorderPane loginPane = new BorderPane();
        loginPane.setStyle("-fx-background-color: #F5F5DC;");

        // Login header
        HBox loginHeader = new HBox();
        loginHeader.setStyle("-fx-background-color: #228B22;");
        loginHeader.setPadding(new Insets(10, 10, 10, 10));
        loginHeader.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome to Easy Bank, please sign in!");
        welcomeLabel.setFont(Font.font("Arial", 20));
        welcomeLabel.setTextFill(Color.WHITE);

        loginHeader.getChildren().add(welcomeLabel);
        loginPane.setTop(loginHeader);

        // Login form
        GridPane loginForm = new GridPane();
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(20, 20, 20, 20));
        loginForm.setVgap(10);
        loginForm.setHgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button signupButton = new Button("Sign Up");

        loginButton.setOnAction(e -> {
            // Add login logic here
            showMainScreen();
        });

        signupButton.setOnAction(e -> {
            showSignupScreen();
        });

        loginForm.add(usernameLabel, 0, 0);
        loginForm.add(usernameField, 1, 0);
        loginForm.add(passwordLabel, 0, 1);
        loginForm.add(passwordField, 1, 1);
        loginForm.add(loginButton, 0, 2);
        loginForm.add(signupButton, 1, 2);

        loginPane.setCenter(loginForm);

        // Login screen scene
        Scene loginScene = new Scene(loginPane, 400, 300);
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // SIGN UP SCREEN
    private void showSignupScreen() {
    BorderPane signupPane = new BorderPane();
    signupPane.setStyle("-fx-background-color: #F5F5DC;");

    // Sign up header
    HBox signupHeader = new HBox();
    signupHeader.setStyle("-fx-background-color: #228B22;");
    signupHeader.setPadding(new Insets(10, 10, 10, 10));
    signupHeader.setAlignment(Pos.CENTER);

    Label signupLabel = new Label("Sign Up");
    signupLabel.setFont(Font.font("Arial", 20));
    signupLabel.setTextFill(Color.WHITE);

    signupHeader.getChildren().add(signupLabel);
    signupPane.setTop(signupHeader);

    // Sign up form
    GridPane signupForm = new GridPane();
    signupForm.setAlignment(Pos.CENTER);
    signupForm.setPadding(new Insets(20, 20, 20, 20));
    signupForm.setVgap(10);
    signupForm.setHgap(10);

    Label firstNameLabel = new Label("First Name:");
    TextField firstNameField = new TextField();
    Label lastNameLabel = new Label("Last Name:");
    TextField lastNameField = new TextField();
    Label emailLabel = new Label("Email:");
    TextField emailField = new TextField();
    Label phoneLabel = new Label("Phone Number:");
    TextField phoneField = new TextField();
    Label newUsernameLabel = new Label("Username:");
    TextField newUsernameField = new TextField();
    Label newPasswordLabel = new Label("Password:");
    PasswordField newPasswordField = new PasswordField();

    Button createAccountButton = new Button("Create Account");
    Button backToLoginButton = new Button("Back to Login");

    createAccountButton.setOnAction(e -> {
        
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String username = newUsernameField.getText();
        String password = newPasswordField.getText();
        
        if (!phone.matches("\\d+")) {
            // Notify user if number input is incorrect
            phoneField.clear();
            phoneField.setText("Invalid phone number");
            return; // Prevent signing-up with an incorrect phone number
        }

        try {
            int nextUserId = getNextUserId(); // Determine next unique userId
            User newUser = new User(nextUserId, firstName, lastName, username, password, email, Long.parseLong(phone));
            writeUserData(newUser);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid phone number");
        }

        createAccountButton.setText("Account Created!");
        createAccountButton.setDisable(true);
    });

    backToLoginButton.setOnAction(e -> showLoginScreen());

    signupForm.add(firstNameLabel, 0, 0);
    signupForm.add(firstNameField, 1, 0);
    signupForm.add(lastNameLabel, 0, 1);
    signupForm.add(lastNameField, 1, 1);
    signupForm.add(emailLabel, 0, 2);
    signupForm.add(emailField, 1, 2);
    signupForm.add(phoneLabel, 0, 3);
    signupForm.add(phoneField, 1, 3);
    signupForm.add(newUsernameLabel, 0, 4);
    signupForm.add(newUsernameField, 1, 4);
    signupForm.add(newPasswordLabel, 0, 5);
    signupForm.add(newPasswordField, 1, 5);
    signupForm.add(createAccountButton, 0, 6);
    signupForm.add(backToLoginButton, 1, 6);

    signupPane.setCenter(signupForm);

    // Sign up scene
    Scene signupScene = new Scene(signupPane, 400, 400);
    primaryStage.setTitle("Sign Up");
    primaryStage.setScene(signupScene);
    primaryStage.show();
}

// function for making user IDs unique
private int getNextUserId() {
    int maxId = 0;
    try (Scanner scanner = new Scanner(new File("src\\main\\data\\users.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length > 0) {
                try {
                    int userId = Integer.parseInt(parts[0]);
                    maxId = Math.max(maxId, userId);
                } catch (NumberFormatException e) {
                    // exception is userID not an integer
                    System.err.println("Invalid userId format in file: " + parts[0]);
                }
            }
        }
    } catch (FileNotFoundException e) {
        return 1;
    }
    return maxId + 1;
}

// function for writing user data to the users.txt file
private void writeUserData(User user) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\data\\users.txt", true))) {
        writer.write(user.getUserId() + "," + user.getFirstName() + "," + user.getLastName() + "," +
                user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "," + user.getPhoneNumber());
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    // MAIN SCREEN
    private void showMainScreen() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F5F5DC;");

        // header
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #228B22;");
        header.setPadding(new Insets(10, 10, 10, 10));
        header.setAlignment(Pos.CENTER);

        Label bankNameLabel = new Label("EASY BANK");
        bankNameLabel.setFont(Font.font("Arial", 20));
        bankNameLabel.setTextFill(Color.WHITE);

        Label helloLabel = new Label("Hello, {name}!"); // Add functionality later
        helloLabel.setFont(Font.font("Arial", 16));
        helloLabel.setTextFill(Color.WHITE);

        Button logoutButton = new Button("Log Out");
        logoutButton.setOnAction(e -> showLoginScreen());

        HBox.setHgrow(bankNameLabel, Priority.ALWAYS);
        bankNameLabel.setMaxWidth(Double.MAX_VALUE);
        bankNameLabel.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(helloLabel, Priority.ALWAYS);
        helloLabel.setMaxWidth(Double.MAX_VALUE);
        helloLabel.setAlignment(Pos.CENTER);

        header.getChildren().addAll(bankNameLabel, helloLabel, logoutButton);
        root.setTop(header);

        // account boxes
        VBox accountBoxes = new VBox(20);
        accountBoxes.setPadding(new Insets(20, 20, 20, 20));
        accountBoxes.setAlignment(Pos.CENTER);

        // Checking
        VBox checkingAccountBox = createAccountBox("CHECKING ACCOUNT", "Random Guy", 1000.0, 500.0);
        accountBoxes.getChildren().add(checkingAccountBox);

        // Savings
        VBox savingsAccountBox = createAccountBox("SAVINGS ACCOUNT", "Random Guy", 2500.0, 1000.0);
        accountBoxes.getChildren().add(savingsAccountBox);

        root.setCenter(accountBoxes);

        // Bottom buttons
        HBox bottomButtons = new HBox(20);
        bottomButtons.setPadding(new Insets(10, 20, 20, 20));
        bottomButtons.setAlignment(Pos.CENTER);

        Button transferButton = new Button("Transfer Money");
        Button openAccountButton = new Button("Open a New Account");

        bottomButtons.getChildren().addAll(transferButton, openAccountButton);
        root.setBottom(bottomButtons);

        // Main scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Easy Banking App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createAccountBox(String accountType, String name, double availableBalance, double currentBalance) {
        VBox accountBox = new VBox(10);
        accountBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px;");
        accountBox.setAlignment(Pos.CENTER);

        Label accountTypeLabel = new Label(accountType);
        Label nameLabel = new Label("Name: " + name);
        Label availableBalanceLabel = new Label("Available Balance: $" + availableBalance);
        Label currentBalanceLabel = new Label("Current Balance: $" + currentBalance);

        Button viewTransactionsButton = new Button("View Transactions");
        // Implement functionality later

        accountBox.getChildren().addAll(accountTypeLabel, nameLabel, availableBalanceLabel, currentBalanceLabel, viewTransactionsButton);
        return accountBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}