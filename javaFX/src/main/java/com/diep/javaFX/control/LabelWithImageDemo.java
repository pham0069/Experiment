package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LabelWithImageDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox box = new HBox(createCalendarLabel());
        Scene scene = new Scene(box, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label createCalendarLabel() throws Exception{
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("calendar.png")));
        image.setFitHeight(100);
        image.setFitWidth(100);
        return new Label("Date", image);
    }
}
