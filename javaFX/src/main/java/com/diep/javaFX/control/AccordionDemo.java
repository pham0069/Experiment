package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccordionDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Accordion Demo");
        Scene scene = new Scene(new VBox(createAccordion()), 400, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private Accordion createAccordion() {
        Accordion accordion = new Accordion();

        TitledPane pane1 = new TitledPane("Boats" , new Label("Show all boats available"));
        TitledPane pane2 = new TitledPane("Cars"  , new Label("Show all cars available"));
        TitledPane pane3 = new TitledPane("Planes", new Label("Show all planes available"));

        accordion.getPanes().add(pane1);
        accordion.getPanes().add(pane2);
        accordion.getPanes().add(pane3);

        return accordion;
    }

}
