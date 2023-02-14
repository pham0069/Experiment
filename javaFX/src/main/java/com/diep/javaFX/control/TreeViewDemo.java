package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TreeViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TreeView Demo");
        VBox box = new VBox(createTreeView());
        Scene scene = new Scene(box, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TreeView createTreeView() {
        TreeView treeView = new TreeView();
        TreeItem rootItem = new TreeItem("Tutorials");

        TreeItem webItem = new TreeItem("Web Tutorials");
        webItem.getChildren().add(new TreeItem("HTML  Tutorial"));
        webItem.getChildren().add(new TreeItem("HTML5 Tutorial"));
        webItem.getChildren().add(new TreeItem("CSS Tutorial"));
        webItem.getChildren().add(new TreeItem("SVG Tutorial"));
        rootItem.getChildren().add(webItem);

        TreeItem javaItem = new TreeItem("Java Tutorials");
        javaItem.getChildren().add(new TreeItem("Java Language"));
        javaItem.getChildren().add(new TreeItem("Java Collections"));
        javaItem.getChildren().add(new TreeItem("Java Concurrency"));
        rootItem.getChildren().add(javaItem);

        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);// Hide Tutorials root
        return treeView;
    }

}
