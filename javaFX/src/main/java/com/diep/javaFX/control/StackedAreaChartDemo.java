package com.diep.javaFX.control;

import javafx.scene.Node;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;

/**
 *  A stacked area chart is similar to an area chart with multiple data series, except a stacked area chart displays
 *  the data series (and thus areas) stacked on top of each other, where the normal area chart would overlap them.
 */
public class StackedAreaChartDemo extends AbstractJavaFXDemo {
    @Override
    protected String getPrimaryStageTitle(){
        return "StackedAreaChartDemo Demo";
    }
    @Override
    protected Node createNode() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("7 Day Interval");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Visits");

        StackedAreaChart chart = new StackedAreaChart(xAxis, yAxis);
        addDataSeries(chart);

        return chart;
    }

    private void addDataSeries(XYChart chart) {
        chart.getData().addAll(createDataSeries1(), createDataSeries2());
    }

    private XYChart.Series createDataSeries1() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Desktop");

        dataSeries1.getData().add(new XYChart.Data( 0, 567));
        dataSeries1.getData().add(new XYChart.Data( 1, 612));
        dataSeries1.getData().add(new XYChart.Data( 2, 800));
        dataSeries1.getData().add(new XYChart.Data( 3, 780));
        dataSeries1.getData().add(new XYChart.Data( 4, 650));
        dataSeries1.getData().add(new XYChart.Data( 5, 610));
        dataSeries1.getData().add(new XYChart.Data( 6, 590));

        return dataSeries1;
    }

    private XYChart.Series createDataSeries2() {
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("Mobile");

        dataSeries2.getData().add(new XYChart.Data( 0, 101));
        dataSeries2.getData().add(new XYChart.Data( 1, 110));
        dataSeries2.getData().add(new XYChart.Data( 2, 140));
        dataSeries2.getData().add(new XYChart.Data( 3, 132));
        dataSeries2.getData().add(new XYChart.Data( 4, 115));
        dataSeries2.getData().add(new XYChart.Data( 5, 109));
        dataSeries2.getData().add(new XYChart.Data( 6, 105));

        return dataSeries2;
    }
}
