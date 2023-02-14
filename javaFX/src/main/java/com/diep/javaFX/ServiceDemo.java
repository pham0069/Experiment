package com.diep.javaFX;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Service class is designed to execute a Task object on one or several background threads. The Service class methods
 * and states must only be accessed on the JavaFX Application thread. The purpose of this class is to help the developer
 * to implement the correct interaction between the background threads and the JavaFX Application thread.
 *
 * Using the Service class, you can observe the state of the background work and optionally cancel it. Later, you can reset the service and restart it. Thus, the service can be defined declaratively and restarted on demand.
 */
public class ServiceDemo extends Application {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Service Demo");
        primaryStage.setScene(new Scene(createLoadingView(), 200, 200));
        primaryStage.show();
    }

    private VBox createLoadingView() {
        Button button = new Button("Load");
        Service service = createService();
        button.setOnAction(e -> {
            service.start();
            button.setDisable(true);
        });
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                System.out.println("Done:" + t.getSource().getValue());
                button.setText("Done");
            }
        });

        VBox box = new VBox(button);
        return box;
    }

    private Service createService() {
        FirstLineService service = new FirstLineService();
        service.setUrl("http://google.com");
        return service;
    }

    private static class FirstLineService extends Service<String> {
        private StringProperty url = new SimpleStringProperty();

        final void setUrl(String value) {
            url.set(value);
        }

        final String getUrl() {
            return url.get();
        }

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws IOException {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(getUrl()).openStream()))) {
                        System.out.println(in.readLine());
                        return in.readLine();
                    }
                }
            };
        }
    }

}
