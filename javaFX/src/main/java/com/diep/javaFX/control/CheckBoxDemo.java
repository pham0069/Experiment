package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CheckBoxDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CheckBox Demo");
        HBox box = new HBox(createCheckBox());
        Scene scene = new Scene(box, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private CheckBox createCheckBox() {
        CheckBox checkBox = new CheckBox("Green");
        // Allow checkbox indeterminate, neither selected, nor not selected
        checkBox.setAllowIndeterminate(true);
        checkBox.setOnAction(e -> {
            if (checkBox.isIndeterminate())
                System.out.println("Checkbox is indeterminate");
            else if (checkBox.isSelected())
                System.out.println("Checkbox is checked");
            else
                System.out.println("Checkbox is unchecked");
        });
        return checkBox;
    }
}
