package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ImageViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ImageView Demo");

        HBox hbox = new HBox(createImageView());
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView createImageView() throws Exception{
        Image image = new Image(getClass().getResourceAsStream("cat.jpeg"));
        return new ImageView(image);
    }
}
