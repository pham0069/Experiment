package com.diep.javaFX.control;

import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

/**
 * Stacked bar chart is 2D graph with X and Y axis
 * Each bar stacks the values of same category from different data series on top of each other,
 * instead of displaying them next to each oth3er like bar chart
 */
public class StackedBarChartDemo extends AbstractJavaFXDemo {
    @Override
    protected String getPrimaryStageTitle(){
        return "StackedBarChart Demo";
    }
    @Override
    protected Node createNode() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Devices");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        StackedBarChart<String, Number> chart = new StackedBarChart<>(xAxis, yAxis);
        addDataSeries(chart);

        return chart;
    }

    private void addDataSeries(StackedBarChart barChart) {
        barChart.getData().addAll(createDataSeries1(), createDataSeries2(), createDataSeries3());
    }

    private XYChart.Series createDataSeries1() {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Desktop");

        dataSeries.getData().add(new XYChart.Data("2014", 567));
        dataSeries.getData().add(new XYChart.Data("2015", 540));

        return dataSeries;
    }

    private XYChart.Series createDataSeries2() {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Phone");

        dataSeries.getData().add(new XYChart.Data("2014"  , 65));
        dataSeries.getData().add(new XYChart.Data("2015"  , 120));

        return dataSeries;
    }

    private XYChart.Series createDataSeries3() {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Tablet");

        dataSeries.getData().add(new XYChart.Data("2014"  , 23));
        dataSeries.getData().add(new XYChart.Data("2015"  , 36));

        return dataSeries;
    }
}
