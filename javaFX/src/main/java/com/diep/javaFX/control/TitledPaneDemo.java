package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TitledPaneDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TitledPane Demo");
        Scene scene = new Scene(new VBox(createTitledPane()), 400, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private TitledPane createTitledPane(){
        Label label = new Label("The content inside the TitledPane");
        TitledPane titledPane = new TitledPane("The Title", label);
        titledPane.setExpanded(true);
        titledPane.setCollapsible(false);
        return titledPane;
    }
}
