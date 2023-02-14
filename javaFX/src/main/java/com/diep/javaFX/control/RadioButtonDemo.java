package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RadioGroup Demo");

        ToggleGroup radioGroup = createRadioGroup();
        VBox box = new VBox(radioGroup.getToggles().toArray(new ToggleButton[0]));

        Scene scene = new Scene(box, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ToggleGroup createRadioGroup() {
        RadioButton radioButton1 = new RadioButton("Left");
        RadioButton radioButton2 = new RadioButton("Right");
        RadioButton radioButton3 = new RadioButton("Up");
        RadioButton radioButton4 = new RadioButton("Down");

        ToggleGroup radioGroup = new ToggleGroup();

        radioButton1.setToggleGroup(radioGroup);
        radioButton2.setToggleGroup(radioGroup);
        radioButton3.setToggleGroup(radioGroup);
        radioButton4.setToggleGroup(radioGroup);

        radioButton1.setOnAction(e -> {
            if (radioButton1.isSelected())
                System.out.println(radioButton1.getText() + " selected");
        });

        return radioGroup;
    }
}
