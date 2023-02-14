package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/button.htm
 *
 */
public class ButtonDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Button Demo");
        VBox box = new VBox(20, createButtons());
        Scene scene = new Scene(box, 300, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button[] createButtons() {
        return new Button[] {
                createEmptyButton(),
                createTextButton(),
                createTextButtonWithGraphic()
        };
    }

    private Button createEmptyButton() {
        return new Button();
    }

    private Button createTextButton() {
        return new Button("Accept");
    }

    private Button createTextButtonWithGraphic() {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("ok.jpeg")));
        image.setFitHeight(50);
        image.setFitWidth(50);
        return new Button("Accept", image);
    }
}
