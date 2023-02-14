package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChoiceBoxDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ChoiceBox Demo");
        HBox box = new HBox(createChoiceBox());

        Scene scene = new Scene(box, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ChoiceBox createChoiceBox() {
        ChoiceBox choiceBox = new ChoiceBox<>();

        choiceBox.getItems().add("Choice 1");
        choiceBox.getItems().add("Choice 2");
        choiceBox.getItems().add("Choice 3");

        choiceBox.setOnAction(e -> System.out.println(choiceBox.getValue() + " selected"));

        return choiceBox;
    }
}
