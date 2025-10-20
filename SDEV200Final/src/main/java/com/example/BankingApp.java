package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
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
        String username = usernameField.getText();
        String password = passwordField.getText();

        User loggedInUser = authenticateUser(username, password);

        if (loggedInUser != null) {
            // Login successful
            System.out.println("Login successful for user: " + username);
            showMainScreen(loggedInUser);
        } else {
            // Login failed
            System.out.println("Login failed for user: " + username);
            Label errorLabel = new Label("Invalid username or password.");
            errorLabel.setTextFill(Color.RED);
            loginForm.add(errorLabel, 0, 3, 2, 1);
        }
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

    // Authenticate the user logging in
    public User authenticateUser(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    Long phoneNumber = resultSet.getLong("phone_number");
                    // Retrieve other info
                    return new User(userId, firstName, lastName, username, password, email, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        // Notify user if phone number input is incorrect
        phoneField.clear();
        phoneField.setText("Invalid phone number");
        return; // Prevent signing up with incorrect phone number
    }

    try {
        int nextUserId = getNextUserId();
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
        try (Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(user_id) AS maxId FROM users")) {
            if (resultSet.next()) {
                maxId = resultSet.getInt("maxId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId + 1;
    }

    // function for writing user data to the users.txt file
    private void writeUserData(User user) {
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (user_id, first_name, last_name, username, password, email, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getEmail());
            statement.setLong(7, user.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
    }
}

    // MAIN SCREEN
    private void showMainScreen(User loggedInUser) {
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

        Label helloLabel = new Label("Hello, " + loggedInUser.getFirstName() + "!");
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

    // User's accounts
    Account checkingAccount = getAccountByType(loggedInUser.getUserId(), "CHECKING");
    if (checkingAccount == null) {
        checkingAccount = createAccount(loggedInUser.getUserId(), "CHECKING");
    }
    Account savingsAccount = getAccountByType(loggedInUser.getUserId(), "SAVINGS");
    if (savingsAccount == null) {
        savingsAccount = createAccount(loggedInUser.getUserId(), "SAVINGS");
    }

    loggedInUser.setCheckingAccount(checkingAccount);
    loggedInUser.setSavingsAccount(savingsAccount);

    VBox accountBoxes = new VBox(20);
    accountBoxes.setPadding(new Insets(20, 20, 20, 20));
    accountBoxes.setAlignment(Pos.CENTER);

    accountBoxes.getChildren().add(createAccountBox(
        checkingAccount.getAccountType(),
        loggedInUser.getFirstName() + " " + loggedInUser.getLastName(),
        checkingAccount.getBalance(),
        checkingAccount.getBalance()));

    accountBoxes.getChildren().add(createAccountBox(
        savingsAccount.getAccountType(),
        loggedInUser.getFirstName() + " " + loggedInUser.getLastName(),
        savingsAccount.getBalance(),
        savingsAccount.getBalance()));

    root.setCenter(accountBoxes);
    

    // Bottom buttons
    HBox bottomButtons = new HBox(20);
    bottomButtons.setPadding(new Insets(10, 20, 20, 20));
    bottomButtons.setAlignment(Pos.CENTER);

    Button transferButton = new Button("Transfer Money");
    transferButton.setOnAction(e -> showTransferDialog(loggedInUser));
    
    Button viewTransactionsButton = new Button("View Transactions");
    viewTransactionsButton.setOnAction(e -> showTransactionsDialog(loggedInUser));

    bottomButtons.getChildren().addAll(transferButton, viewTransactionsButton);
    root.setBottom(bottomButtons);

    // Main scene
    Scene scene = new Scene(root, 800, 600);
    primaryStage.setTitle("Easy Banking App");
    primaryStage.setScene(scene);
    primaryStage.show();
}
    // Makes account boxes
    private VBox createAccountBox(String accountType, String name, double availableBalance, double currentBalance) {
    VBox accountBox = new VBox(10);
    accountBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10px;");
    accountBox.setAlignment(Pos.CENTER);

    Label accountTypeLabel = new Label(accountType);
    Label availableBalanceLabel = new Label("Available Balance: $" + availableBalance);
    /* If users could pay with this bank outside of the program, this would display
    * their current balance whilst a transaction was still pending/processing. Since transactions are
    * instantaneous in this example, the current balance is the same as the available balance. */   
    Label currentBalanceLabel = new Label("Current Balance: $" + currentBalance);

        accountBox.getChildren().addAll(accountTypeLabel, availableBalanceLabel, currentBalanceLabel);
        return accountBox;
    }

private Account createAccount(int userId, String accountType) {
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts (user_id, balance, account_type) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
        statement.setInt(1, userId);
        statement.setDouble(2, 500.0); // Set initial balance to 500 for testing
        statement.setString(3, accountType);
        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int accountId = generatedKeys.getInt(1);
                return new Account(accountId, accountType, userId, 500.0);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}

private Account getAccountByType(int userId, String accountType) {
    Account account = null;
    String sql = "SELECT account_id, balance FROM accounts WHERE user_id = ? AND account_type = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        stmt.setString(2, accountType);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int accountId = rs.getInt("account_id");
                double balance = rs.getDouble("balance");
                account = new Account(accountId, accountType, userId, balance);
                account.loadAccountData();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return account;
}

// TRANSACTION STUFF

private void showTransferDialog(User loggedInUser) {
    Stage transferStage = new Stage();
    transferStage.initModality(Modality.APPLICATION_MODAL);
    transferStage.initOwner(primaryStage);
    transferStage.setTitle("Transfer Money");

    GridPane transferGrid = new GridPane();
    transferGrid.setAlignment(Pos.CENTER);
    transferGrid.setHgap(10);
    transferGrid.setVgap(10);
    transferGrid.setPadding(new Insets(20, 20, 20, 20));

    Label fromLabel = new Label("From:");
    ComboBox<String> fromComboBox = new ComboBox<>();
    fromComboBox.getItems().addAll("Checking Account", "Savings Account");
    fromComboBox.setValue("Checking Account");

    Label toLabel = new Label("To:");
    ComboBox<String> toComboBox = new ComboBox<>();
    toComboBox.getItems().addAll("Checking Account", "Savings Account");
    toComboBox.setValue("Savings Account");

    Label amountLabel = new Label("Amount:");
    TextField amountField = new TextField();

    Button transferButton = new Button("Transfer");
    transferButton.setOnAction(e -> {
        String fromAccount = fromComboBox.getValue();
        String toAccount = toComboBox.getValue();

        // Stop user from choosing nothing
        if (fromAccount == null || toAccount == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Select accounts");
            alert.setContentText("Please choose both From and To accounts.");
            alert.showAndWait();
            return;
        }

        // Stop user from choosing same account to transfer to
        if (fromAccount.equals(toAccount)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Same account");
            alert.setContentText("From and To accounts cannot be the same.");
            alert.showAndWait();
            return;
        }

            double amount;
        try {
            amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid amount");
            alert.setContentText("Please enter a valid positive number.");
            alert.showAndWait();
            return;
        }

        // Fetch accounts from user
    Account from = fromAccount.equals("Checking Account") ? loggedInUser.getCheckingAccount() : loggedInUser.getSavingsAccount();
    Account to = toAccount.equals("Checking Account") ? loggedInUser.getCheckingAccount() : loggedInUser.getSavingsAccount();

        if (from == null || to == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Account not found");
            alert.setContentText("Couldn't find user's account.'");
            alert.showAndWait();
            return;
        }

        // Do transfer after checkign balance
        if (from.getBalance() >= amount) {
            transferMoney(from, to, amount);
            transferStage.close();

            showMainScreen(loggedInUser);
        } else {
            // Alert for insufficient balance
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient balance");
            alert.setContentText("You don't have enough money to make this transaction.");
            alert.showAndWait();
        }
    });

    transferGrid.add(fromLabel, 0, 0);
    transferGrid.add(fromComboBox, 1, 0);
    transferGrid.add(toLabel, 0, 1);
    transferGrid.add(toComboBox, 1, 1);
    transferGrid.add(amountLabel, 0, 2);
    transferGrid.add(amountField, 1, 2);
    transferGrid.add(transferButton, 0, 3, 2, 1);

    Scene transferScene = new Scene(transferGrid, 300, 200);
    transferStage.setScene(transferScene);
    transferStage.show();
}

private void transferMoney(Account fromAccount, Account toAccount, double amount) {
    if (fromAccount.getBalance() >= amount) {
        // withdraw and deposit function
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        Transaction outgoingTransaction = new Transaction(
                fromAccount.getAccountId(),
                fromAccount.getAccountType(),
                fromAccount.getUserId(),
                fromAccount.getBalance()  // current balance after withdrawal
        );
        outgoingTransaction.makeTransaction(amount, "TRANSFER_OUT");

        Transaction incomingTransaction = new Transaction(
                toAccount.getAccountId(),
                toAccount.getAccountType(),
                toAccount.getUserId(),
                toAccount.getBalance()    // current balance after deposit
        );
        incomingTransaction.makeTransaction(amount, "TRANSFER_IN");

        // Save transactions
        saveTransaction(outgoingTransaction);
        saveTransaction(incomingTransaction);

        System.out.println("Transfer of $" + amount + " from " + fromAccount.getAccountType() +
                " to " + toAccount.getAccountType() + " completed successfully.");
    } else {
        System.out.println("Insufficient balance.");
    }
}

// Save transaction data to database
private void saveTransaction(Transaction transaction) {
    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO transactions (user_id, account_id, amount, date, type) VALUES (?, ?, ?, ?, ?)")) {
        statement.setInt(1, transaction.getUserId());
        statement.setInt(2, transaction.getAccountId());
        statement.setDouble(3, transaction.getAmount());
        statement.setDate(4, new java.sql.Date(transaction.getDate().getTime()));
        statement.setString(5, transaction.getType());
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void showTransactionsDialog(User loggedInUser) {
  Stage dialog = new Stage();
  dialog.initModality(Modality.APPLICATION_MODAL);
  dialog.initOwner(primaryStage);
  dialog.setTitle("Transaction History");
  TableView < Transaction > table = new TableView < > ();
  table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

  // Make a table to show the transactions
  // Date of transaction
  TableColumn < Transaction, String > colDate = new TableColumn < > ("Date");
  colDate.setCellValueFactory(cell -> {
    java.util.Date d = cell.getValue().getDate();
    String s = (d == null) ? "" : new java.text.SimpleDateFormat("yyyy-MM-dd").format(d);
    return new javafx.beans.property.SimpleStringProperty(s);
  });

  // Transaction type
  TableColumn < Transaction, String > colType = new TableColumn < > ("Type");
  colType.setCellValueFactory(cell ->
    new javafx.beans.property.SimpleStringProperty(cell.getValue().getType())
  );

  // Account type
  TableColumn < Transaction, String > colAccount = new TableColumn < > ("Account");
  colAccount.setCellValueFactory(cell ->
    new javafx.beans.property.SimpleStringProperty(cell.getValue().getAccountType())
  );

  // Amount transferred
  TableColumn < Transaction, String > colAmount = new TableColumn < > ("Amount");
  colAmount.setCellValueFactory(cell -> {
    String s = String.format("$%,.2f", cell.getValue().getAmount());
    return new javafx.beans.property.SimpleStringProperty(s);
  });

  table.getColumns().addAll(colDate, colType, colAccount, colAmount);

  // Load transactions
  table.getItems().setAll(getUserTransactions(loggedInUser.getUserId()));

  VBox root = new VBox(10, table);
  root.setPadding(new Insets(10));
  root.setPrefSize(700, 450);

  Scene scene = new Scene(root);
  dialog.setScene(scene);
  dialog.showAndWait();
}

private List < Transaction > getUserTransactions(int userId) {
  List < Transaction > list = new ArrayList < > ();
  String sql = "SELECT t.transaction_id, t.account_id, t.user_id, t.amount, t.type, t.date, a.account_type " +
    "FROM Transactions t " +
    "JOIN Accounts a ON t.account_id = a.account_id " +
    "WHERE t.user_id = ? " +
    "ORDER BY t.date DESC, t.transaction_id DESC";
  try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setInt(1, userId);
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        int accountId = rs.getInt("account_id");
        String accountType = rs.getString("account_type");
        double amount = rs.getDouble("amount");
        String type = rs.getString("type");
        java.sql.Timestamp ts = rs.getTimestamp("date");
        java.util.Date date = (ts != null) ? new java.util.Date(ts.getTime()) : null;
        Transaction tr = new Transaction(accountId, accountType, userId, 0.0);
        tr.setAmount(amount);
        tr.setType(type);
        tr.setDate(date);
        list.add(tr);
      }
    }
  } catch (SQLException ex) {
    ex.printStackTrace();
  }
  return list;
}

    public static void main(String[] args) {
        launch(args);
    }
}