package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class AbstractJavaFXDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(getPrimaryStageTitle());
        Scene scene = new Scene(createRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected abstract String getPrimaryStageTitle();
    protected Parent createRoot() {
        return new VBox(createNode());
    }
    protected abstract Node createNode();
}
