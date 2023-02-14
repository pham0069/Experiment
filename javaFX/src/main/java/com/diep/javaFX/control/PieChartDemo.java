package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Pie chart is a circle divided into slices, each slice corresponding to the contribution of a single data
 */
public class PieChartDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PieChart Demo");
        VBox box = new VBox(createPieChart());
        Scene scene = new Scene(box, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();

        PieChart.Data slice1 = new PieChart.Data("Desktop", 213);
        PieChart.Data slice2 = new PieChart.Data("Phone"  , 67);
        PieChart.Data slice3 = new PieChart.Data("Tablet" , 36);

        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);

        return pieChart;
    }
}
