package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollPaneDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ScrollPane Demo");
        Scene scene = new Scene(new VBox(createScrollPane()), 400, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("cat.jpeg")));

        scrollPane.setContent(imageView);
        // Allow moving content along both X and Y axis simultaneously
        scrollPane.pannableProperty().set(true);
        // Make scrollPane fit its content to the width of the viewport.
        scrollPane.fitToWidthProperty().set(true);
        // Make scrollPane fit its content to the height of the viewport.
        // This is ignored if content is not resizable
        scrollPane.fitToHeightProperty().set(true);

        // Show or hide scrollbars via ScrollbarPolicy
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return scrollPane;
    }
}
