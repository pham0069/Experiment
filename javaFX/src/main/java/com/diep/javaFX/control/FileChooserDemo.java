package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


/**
 *
 */
public class FileChooserDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FileChooser Demo");
        HBox hbox = new HBox(10, createFileChooserButton(primaryStage), createDirChooserButton(primaryStage));

        Scene scene = new Scene(hbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Button createFileChooserButton(Stage primaryStage){
        Button button = new Button("Select File");
        button.setTooltip(new Tooltip("Select file for fun"));
        button.setOnAction(e -> {
            File selectedFile = new FileChooser().showOpenDialog(primaryStage);
            System.out.println("Selected file is " + selectedFile);
        });

        return button;
    }

    private Button createDirChooserButton(Stage primaryStage){
        Button button = new Button("Select Directory");
        button.setOnAction(e -> {
            File selectedFile = new DirectoryChooser().showDialog(primaryStage);
            System.out.println("Selected directory is " + selectedFile);
        });

        return button;
    }

}
