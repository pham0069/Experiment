package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HyperLinkDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HyperLink Demo");
        VBox vBox = new VBox(createHyperLink());
        Scene scene = new Scene(vBox, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Hyperlink createHyperLink() {
        Hyperlink link = new Hyperlink("Click Me!");
        link.setOnAction(e -> System.out.println("HyperLink was clicked"));
        return link;
    }


}
