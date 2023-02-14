package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PaginationDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pagination Demo");
        VBox box = new VBox(createPagination());
        Scene scene = new Scene(box, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pagination createPagination() {
        Pagination pagination = new Pagination();

        pagination.setPageCount(21);
        pagination.setCurrentPageIndex(3);
        pagination.setMaxPageIndicatorCount(3);

        setPageFactory(pagination);

        return pagination;
    }

    /**
     * Pagination factory is called when user navigates to a new page
     * It must implement CallBack<Integer, Node> that returns a Node given a page index
     * @param pagination
     */
    private void setPageFactory(Pagination pagination) {
        pagination.setPageFactory((pageIndex) -> {

            Label label1 = new Label("Content for page with index: " + pageIndex);
            label1.setFont(new Font("Arial", 24));

            Label label2 = new Label("Main content of the page ...");

            return new VBox(label1, label2);
        });
    }
}
