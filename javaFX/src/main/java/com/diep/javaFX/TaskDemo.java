package com.diep.javaFX;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * SceneGraph is not a thread-safe object. It is designed to execute on single thread only
 * JavaFX app start(), stop() and events are processed on JavaFX App thread.
 * Any live manipulation to the scene should be done on the main app thread
 * Nodes that are not attached to live scene, may be created and manipulated in other threads
 *
 * To avoid unresponsive UI, lengthy processes should not run on JavaFX main thread
 * Several mechanisms for multiple thread JavaFX app:
 * 1. Invoke runLater(Runnable) from any thread, to post the runnable to an event queue and then return immediately
 * to the caller
 * 2. Worker, Task, ScheduledService
 *
 * Worker life cycle:
 * - when created -> READY state
 * - when scheduled for work -> SCHEDULED state
 * - when completed successfully -> SUCCEEDED state
 * - any exception during execution -> FAILED state
 * - interrupted during execution -> CANCELLED state
 *
 *
 * A background task counts from one to one million and a progress bar, and you must update the progress on this
 * progress bar as the counter runs in the background.
 */
public class TaskDemo extends Application {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Task Demo");
        Scene scene = new Scene(createProgressBarView(), 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createProgressBarView() {
        Task task = new Task<Void>() {
            @Override public Void call() {
                final int max = 100;
                for (int i=1; i<=max; i++) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        break;
                    }
                    updateMessage("Iteration " + i);
                    // Update the progress, totalWork, and workDone properties of the task
                    updateProgress(i, max);

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            break;
                        }
                    }
                }
                return null;
            }
        };
        ProgressBar bar = new ProgressBar();
        // Bind the progress of the bar to the progress of the task
        bar.progressProperty().bind(task.progressProperty());
        bar.setVisible(false);


        Button button = new Button("Start!");
        button.setOnAction(e -> {
            button.setDisable(true);
            button.setText("Working");
            new Thread(task).start();
            bar.setVisible(true);
        });
        task.setOnSucceeded(e -> {
            button.setText("Done");
        });

        VBox box = new VBox(10, button, bar);
        return box;
    }
}
