package com.diep.javaFX.control;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Line chart is 2D graph
 * It connects all the points in a data series to each other, in the increasing order of X-axis value
 */
public class LineChartDemo extends AbstractJavaFXDemo {
    @Override
    protected String getPrimaryStageTitle(){
        return "LineChart Demo";
    }
    @Override
    protected Node createNode() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        LineChart chart = new LineChart<>(xAxis, yAxis);
        addDataSeries(chart);

        return chart;
    }

    private void addDataSeries(XYChart chart) {
        chart.getData().addAll(ScatterChartDemo.createDataSeries1());
    }
}
