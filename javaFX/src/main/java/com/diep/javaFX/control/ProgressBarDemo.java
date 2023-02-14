package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressBarDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ProgressBar Demo");
        VBox vBox = new VBox(createProgressBar());
        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ProgressBar createProgressBar() {
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setProgress(0.5);
        return progressBar;
    }
}
