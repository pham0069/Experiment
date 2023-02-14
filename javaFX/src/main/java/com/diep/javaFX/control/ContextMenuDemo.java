package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Right click on the text area to see the menu with 3 choice popup
 */
public class ContextMenuDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ContextMenu Demo");
        HBox hbox = new HBox(createAreaWithContextMenu());

        Scene scene = new Scene(hbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextArea createAreaWithContextMenu() {
        TextArea textArea = new TextArea();
        textArea.setContextMenu(createContextMenu());
        return textArea;
    }

    private ContextMenu createContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Choice 1");
        MenuItem menuItem2 = new MenuItem("Choice 2");
        MenuItem menuItem3 = new MenuItem("Choice 3");

        menuItem3.setOnAction((event) -> {
            System.out.println("Choice 3 clicked!");
        });

        contextMenu.getItems().addAll(menuItem1,menuItem2,menuItem3);
        return contextMenu;
    }
}
