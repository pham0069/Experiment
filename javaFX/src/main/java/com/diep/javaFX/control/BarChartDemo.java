package com.diep.javaFX.control;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Bar chart can draw a bar chart inside JavaFX app, which is useful in dashboard app
 * A bar chart is a 2D graph with X axis and Y axis. Typically X axis shows some category, Y axis shows numerical value
 * A bar chart can have single or multiple data series.
 * It's ok if multiple data series do not have same set of X-axis data
 */
public class BarChartDemo extends AbstractJavaFXDemo {
    @Override
    protected String getPrimaryStageTitle(){
        return "BarChart Demo";
    }
    @Override
    protected Node createNode() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Devices");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        addDataSeries(chart);

        return chart;
    }

    private void addDataSeries(BarChart barChart) {
        barChart.getData().addAll(createDataSeries1(), createDataSeries2());
    }

    private XYChart.Series createDataSeries1() {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("2014");

        dataSeries.getData().add(new XYChart.Data("Desktop", 178));
        dataSeries.getData().add(new XYChart.Data("Phone"  , 65));
        dataSeries.getData().add(new XYChart.Data("Tablet"  , 23));
        return dataSeries;
    }

    private XYChart.Series createDataSeries2() {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("2015");

        dataSeries.getData().add(new XYChart.Data("Desktop", 540));
        dataSeries.getData().add(new XYChart.Data("Phone"  , 120));
        dataSeries.getData().add(new XYChart.Data("Tablet"  , 36));

        return dataSeries;
    }
}
