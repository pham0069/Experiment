package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class LabelDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Label Demo");
        VBox box = new VBox(20, createLabels());
        Scene scene = new Scene(box, 300, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label[] createLabels() {
        return new Label[] {
                createEmptyLabel(),
                createTextLabel(),
                createTextLabelWithGraphic(),
                createTextWrappingLabel(),
                createZoomableLabel()
        };
    }

    private Label createEmptyLabel() {
        return new Label();
    }

    private Label createTextLabel() {
        return new Label("Search");
    }

    private Label createTextLabelWithGraphic() {
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream("search.png")));
        image.setFitWidth(50);
        image.setFitHeight(50);
        Label label = new Label("Search");
        label.setGraphic(image);
        label.setTextFill(Color.web("#0076a3"));
        label.setFont(new Font("Cambria", 25));
        return label;
    }

    private Label createTextWrappingLabel() {
        Label label = new Label("A label that needs to be wrapped - Resize window to seeeeee me flow");
        label.setWrapText(true);
        return label;
    }

    private Label createZoomableLabel() {
        Label label = new Label ("Found");
        label.setFont(new Font("Arial", 40));
        label.setTextFill(Color.web("#cc3300"));
        label.setRotate(270);
        label.setTranslateY(50);

        label.setOnMouseEntered(e -> {
            label.setScaleX(1.5);
            label.setScaleY(1.5);
        });

        label.setOnMouseExited(e -> {
            label.setScaleX(1);
            label.setScaleY(1);
        });
        return label;
    }

}
