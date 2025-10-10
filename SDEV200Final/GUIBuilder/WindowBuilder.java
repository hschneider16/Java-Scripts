import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WindowBuilder extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Awesome Window");

        Pane pane = new Pane();
        pane.setPrefSize(689, 336);
        pane.setStyle("-fx-background-color: #eeeeee;");

        Label userLabel = new Label("Username:");
        userLabel.setLayoutX(12.262496948242188);
        userLabel.setLayoutY(15.012496948242188);
        userLabel.setPrefWidth(105.8125);
        userLabel.setPrefHeight(17.600000381469727);
        userLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lexend.ttf"), 14.00));
        userLabel.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(userLabel);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setLayoutX(11.1875);
        passwordLabel.setLayoutY(55);
        passwordLabel.setPrefWidth(105.8125);
        passwordLabel.setPrefHeight(17.600000381469727);
        passwordLabel.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lexend.ttf"), 14.00));
        passwordLabel.setStyle("-fx-text-fill: #1b1b1b;");
        pane.getChildren().add(passwordLabel);

        TextField element4 = new TextField("");
        element4.setLayoutX(86.26);
        element4.setLayoutY(13.56);
        element4.setPrefWidth(105.81);
        element4.setPrefHeight(23.20);
        element4.setPromptText("Username");
        element4.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lexend.ttf"), 14.00));
        element4.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(element4);

        PasswordField element5 = new PasswordField();
        element5.setText("");
        element5.setLayoutX(86.25);
        element5.setLayoutY(53.00);
        element5.setPrefWidth(105.81);
        element5.setPrefHeight(23.20);
        element5.setPromptText("Password");
        element5.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lexend.ttf"), 14.00));
        element5.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-width: 1px; -fx-border-radius: 2px; -fx-prompt-text-fill: #737674;");
        pane.getChildren().add(element5);

        Button loginButton = new Button("Login");
        loginButton.setLayoutX(10.27);
        loginButton.setLayoutY(95.86);
        loginButton.setPrefWidth(105.81);
        loginButton.setPrefHeight(29.20);
        loginButton.setDisable(false);
        loginButton.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lexend.ttf"), 14.00));
        loginButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
        loginButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { loginButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        loginButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { loginButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(loginButton);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setLayoutX(125.27);
        signUpButton.setLayoutY(95.86);
        signUpButton.setPrefWidth(105.81);
        signUpButton.setPrefHeight(29.20);
        signUpButton.setDisable(false);
        signUpButton.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Lexend.ttf"), 14.00));
        signUpButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #1b1b1b; -fx-border-color: #626262; -fx-border-radius: 4px; -fx-background-radius: 4px; -fx-border-width: 1px;");
        signUpButton.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { signUpButton.setBackground(new Background(new BackgroundFill(Color.web("#c2c2c2"), new CornerRadii(4.00), null))); });
        signUpButton.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> { signUpButton.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(4.00), null))); });
        pane.getChildren().add(signUpButton);

        Scene scene = new Scene(pane, 689, 336);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}