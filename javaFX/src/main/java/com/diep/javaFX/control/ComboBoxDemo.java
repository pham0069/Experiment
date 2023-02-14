package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ComboBox Demo");
        HBox hbox = new HBox(createComboBox());

        Scene scene = new Scene(hbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private ComboBox createComboBox() {
        ComboBox comboBox = new ComboBox();

        comboBox.getItems().add("Choice 1");
        comboBox.getItems().add("Choice 2");
        comboBox.getItems().add("Choice 3");

        /**
         * Allow editing on combo box
         * Selected item may not be in the list
         */
        comboBox.setEditable(true);
        //comboBox.setOnAction(e -> System.out.println(comboBox.getValue() + " selected"));

        return comboBox;
    }

}
