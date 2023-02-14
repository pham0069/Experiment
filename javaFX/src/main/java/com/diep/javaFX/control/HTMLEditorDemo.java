package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class HTMLEditorDemo extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("HTMLEditor Demo");

        VBox vBox = new VBox(createHTMLEditor());
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HTMLEditor createHTMLEditor() {
        HTMLEditor htmlEditor = new HTMLEditor();
        String htmlText = "<b>Bold text</b>";

        htmlEditor.setHtmlText(htmlText);
        return htmlEditor;
    }
}
