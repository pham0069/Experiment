package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SliderDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Slider Demo");
        HBox hbox = new HBox(createSlider());

        Scene scene = new Scene(hbox, 360, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Slider createSlider() {
        Slider slider = new Slider(0, 100, 0);
        // How many units value change when user moves slider 1 tick
        slider.setMajorTickUnit(8.0);
        // How many minor ticks between two major ticks
        slider.setMinorTickCount(3);
        slider.setMinSize(300, 50);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        return slider;
    }
}
