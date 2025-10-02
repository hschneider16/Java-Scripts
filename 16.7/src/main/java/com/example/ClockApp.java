package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClockApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        ClockPane clockPane = new ClockPane(); // Create clock

        // Field for hours
        TextField hourField = new TextField();
        hourField.setPrefWidth(50);
        
        // Field for minutes
        TextField minuteField = new TextField();
        minuteField.setPrefWidth(50);
        
        // Field for seconds
        TextField secondField = new TextField();
        secondField.setPrefWidth(50);

        Label hourLabel = new Label("Hour");
        Label minuteLabel = new Label("Minute");
        Label secondLabel = new Label("Second");

        // Listeners that update the clock when the inputs are changed
        hourField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                int hour = Integer.parseInt(newValue);
                clockPane.setHour(hour);
            }
        });

        minuteField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                int minute = Integer.parseInt(newValue);
                clockPane.setMinute(minute);
            }
        });

        secondField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                int second = Integer.parseInt(newValue);
                clockPane.setSecond(second);
            }
        });

        // Create layout for inputs + clock
        HBox inputBox = new HBox(10, hourLabel, hourField, minuteLabel, minuteField, secondLabel, secondField);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(clockPane);
        borderPane.setBottom(inputBox);

        // Create the scene
        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setTitle("Clock");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}