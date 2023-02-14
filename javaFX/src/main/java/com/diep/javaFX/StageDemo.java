package com.diep.javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageDemo extends Application {
    private final Stage stage = new Stage();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        setStagePosition();
        setStageSize();
        setStageModality(primaryStage);
        setStageStyle();

        // Set full screen
        primaryStage.setScene(new Scene(new VBox()));
        primaryStage.setFullScreen(true);
        // Show and exit immediately
        primaryStage.show();

        // Show and block until stage is closed
        stage.showAndWait();
    }

    private void setStagePosition() {
        stage.setX(50);
        stage.setY(50);
    }

    private void setStageSize() {
        stage.setWidth(600);
        stage.setHeight(300);
    }

    /**
     * Set modality, i.e determining if the window representing the Stage will block other windows opened by the same
     * JavaFX app
     * APPLICATION_MODAL: new stage will block all other stages until it is closed
     * WINDOW_MODAL: new stage will block its owning stage
     * NONE: not block anything
     */
    private void setStageModality(Stage owner) {
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        //stage.initModality(Modality.NONE);
    }

    private void setStageStyle() {
        stage.initStyle(StageStyle.DECORATED);
        //stage.initStyle(StageStyle.UNDECORATED);
        //stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initStyle(StageStyle.UNIFIED);
        //stage.initStyle(StageStyle.UTILITY);
    }
}
