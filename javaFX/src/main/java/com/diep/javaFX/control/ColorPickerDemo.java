package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorPickerDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ColorPicker Demo");
        HBox hbox = new HBox(createColorPicker());

        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ColorPicker createColorPicker() {
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(e -> {
            Color value = colorPicker.getValue();
            System.out.println("Picking color " + value);
        });
        return colorPicker;
    }
}
