package com.diep.javaFX.control;

import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * Scatter chart is 2D graph, typically both axis shows some numerical value
 * Each point is located with X and Y coordinator. The points are scattered in the graph with no connection
 */
public class ScatterChartDemo extends AbstractJavaFXDemo {
    @Override
    protected String getPrimaryStageTitle(){
        return "ScatterChart Demo";
    }
    @Override
    protected Node createNode() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("No of employees");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue per employee");

        ScatterChart chart = new ScatterChart(xAxis, yAxis);
        addDataSeries(chart);

        return chart;
    }

    private void addDataSeries(XYChart chart) {
        chart.getData().addAll(createDataSeries1());
    }

    public static XYChart.Series createDataSeries1() {
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("2014");
        dataSeries.getData().add(new XYChart.Data(20, 850));
        dataSeries.getData().add(new XYChart.Data( 1, 567));
        dataSeries.getData().add(new XYChart.Data( 5, 612));
        dataSeries.getData().add(new XYChart.Data(10, 800));
        dataSeries.getData().add(new XYChart.Data(20, 780));
        dataSeries.getData().add(new XYChart.Data(40, 810));
        dataSeries.getData().add(new XYChart.Data(80, 850));

        return dataSeries;
    }


}
