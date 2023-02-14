package com.diep.javaFX.control;

import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * Area chart is a 2D graph
 * It is a line chart where the area below the lines are painted with colors
 */
public class AreaChartDemo extends AbstractJavaFXDemo {
    @Override
    protected String getPrimaryStageTitle(){
        return "AreaChart Demo";
    }
    @Override
    protected Node createNode() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        AreaChart chart = new AreaChart(xAxis, yAxis);
        addDataSeries(chart);

        return chart;
    }

    private void addDataSeries(XYChart chart) {
        chart.getData().addAll(ScatterChartDemo.createDataSeries1());
    }
}
