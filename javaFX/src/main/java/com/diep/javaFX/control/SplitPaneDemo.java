package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplitPaneDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("SplitPane Demo");
        Scene scene = new Scene(new VBox(createSplitPane()));
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private SplitPane createSplitPane() {
        SplitPane splitPane = new SplitPane();

        VBox leftControl  = new VBox(new Label("Left Control"));
        VBox midControl   = new VBox(new Label("Mid Control"));
        VBox rightControl = new VBox(new Label("Right Control"));

        splitPane.getItems().addAll(leftControl, midControl, rightControl);
        splitPane.setMinSize(600, 300);
        splitPane.setDividerPositions(100, 200);

        return splitPane;
    }
}
