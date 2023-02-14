package com.diep.javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * javafx.application.Application class is where to launch a Java FX app
 * Show a simple window with proper title and label
 */
public class HelloFX extends Application {
    /**
     * Entry point of JavaFX applications
     * primaryStage is where all visual parts are displayed and is created for u by JavaFX runtime
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("First JavaFX App");
        primaryStage.setScene(createHelloScene());
        // Without show(), the app will not be visible
        primaryStage.show();
    }

    private Scene createHelloScene() {
        Label label = new Label("Hello World, JavaFX !");
        return new Scene(label, 400, 200);
    }

    /**
     * Not required for JavaFX apps when JAR file is created with JavaFX Packager tool,
     * which embeds JavaFX Launcher in JAR file
     * Useful to include main() to run JAR file without JavaFX Launcher
     * and to run Swing apps embedded with JavaFX code
     * @param args
     */
    public static void main(String[] args) {
        // Launch JavaFX runtime and JavaFX app
        HelloFX.launch(args);
    }
}
