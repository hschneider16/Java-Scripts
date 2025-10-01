package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Image Grid");

        // Create gridpane
        GridPane gridPane = new GridPane();

        // Load images
        Image image1 = new Image(getClass().getResourceAsStream("/images/flag1.gif"));
        Image image2 = new Image(getClass().getResourceAsStream("/images/flag2.gif"));
        Image image3 = new Image(getClass().getResourceAsStream("/images/flag6.gif"));
        Image image4 = new Image(getClass().getResourceAsStream("/images/flag7.gif"));

        // Create imageviews
        ImageView imageView1 = new ImageView(image1);
        ImageView imageView2 = new ImageView(image2);
        ImageView imageView3 = new ImageView(image3);
        ImageView imageView4 = new ImageView(image4);

        // Setting fixed size (the French flag was really small compared to the other flags + the example)
        double imageSize = 400; // Set the desired size
        imageView1.setFitWidth(imageSize);
        imageView1.setFitHeight(imageSize);
        imageView1.setPreserveRatio(true);

        imageView2.setFitWidth(imageSize);
        imageView2.setFitHeight(imageSize);
        imageView2.setPreserveRatio(true);

        imageView3.setFitWidth(imageSize);
        imageView3.setFitHeight(imageSize);
        imageView3.setPreserveRatio(true);

        imageView4.setFitWidth(imageSize);
        imageView4.setFitHeight(imageSize);
        imageView4.setPreserveRatio(true);

        // Add imageviews to the gridpane
        gridPane.add(imageView1, 0, 0);
        gridPane.add(imageView2, 1, 0);
        gridPane.add(imageView3, 0, 1);
        gridPane.add(imageView4, 1, 1);

        // Set scene
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}