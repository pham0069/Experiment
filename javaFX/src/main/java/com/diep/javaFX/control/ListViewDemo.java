package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class ListViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ListView Demo");
        ListView listView = createListView();
        Button button = createMonitorButton(listView);
        HBox hbox = new HBox(listView, button);

        Scene scene = new Scene(hbox, 500, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ListView createListView() {
        ListView listView = new ListView<>();

        listView.getItems().add("Item 1");
        listView.getItems().add("Item 2");
        listView.getItems().add("Item 3");

        return listView;
    }

    private Button createMonitorButton(ListView listView) {
        Button button = new Button("Read Selected Value");

        button.setOnAction(event -> {
            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

            for(Object o : selectedIndices){
                System.out.println("o = " + o + " (" + o.getClass() + ")");
            }
        });

        return button;
    }

}
