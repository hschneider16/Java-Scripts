package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating the circle and centering it
        Circle circle = new Circle(100, Color.WHITE);
        circle.setCenterX(150);
        circle.setCenterY(150);

        // Event handlers for the mouse being pressed/released
        circle.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> circle.setFill(Color.BLACK));
        circle.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> circle.setFill(Color.WHITE));

        Pane root = new Pane(circle);
        // Changing BG color so circle can be seen
        root.setStyle("-fx-background-color: lightgreen;");
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Change color using a mouse");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}