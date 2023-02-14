package com.diep.javaFX.skewPod;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class SkewPod extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Skew Pod");
        Scene scene = new Scene(createView());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private GridPane createView() {
        HBox buttons = new HBox(10, createResetAllButton(), createDisableButton());
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        buttons.setPadding(new Insets(20));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(createSymbolComboBox(), 0, 0);
        gridPane.add(createFirmLabel(), 1, 0);
        gridPane.add(createTableView(), 0, 1, 2, 1);
        gridPane.add(buttons, 0, 2, 2, 1);

        return gridPane;
    }

    private ComboBox createSymbolComboBox() {
        ObservableList<String> items = FXCollections.observableArrayList("EUR/USD", "GBP/USD", "USD/JPY");
        ComboBox ccyPair = new ComboBox(items);
        ccyPair.getSelectionModel().select(1);
        return ccyPair;
    }

    private Label createFirmLabel() {
        return new Label("SGCSAL@SG_SALES");
    }
    private Button createResetAllButton() {
        return new Button("Reset All");
    }

    private Button createDisableButton() {
        return new Button("Disable");
    }

    private Depth[] createDepths() {
        return new Depth[] {
                new Depth("1M", "1M", 1.36539, 1.3654, 0.6),
                new Depth("1M", "1M", 1.36541, 1.36544, 0.3),
                new Depth("3M", "3M", 1.36539, 1.36545, 0.6),
                new Depth("5M", "5M", 1.36538, 1.36546, 0.8),
                new Depth("10M", "10M", 1.36537, 1.36547, 1.0),
                new Depth("15M", "15M", 1.36535, 1.36549, 1.4),
                new Depth("20M", "20M", 1.36532, 1.36552, 2.0),
                new Depth("30M", "30M", 1.36529, 1.36555, 2.6)
        };
    }

    private TableView createTableView() {
        TableView tableView = new TableView();
        String[] depthProperties = new String[] {"bidSize", "bidPrice", "spread", "askPrice", "askSize"};

        for (String property : depthProperties) {
            TableColumn<String, Depth> column = new TableColumn<>(property);
            column.setCellValueFactory(new PropertyValueFactory<>(property));
            tableView.getColumns().add(column);

        }

        tableView.getItems().addAll(createDepths());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        hideTableHeader(tableView);
        return tableView;
    }

    private void hideTableHeader(TableView table) {
        table.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
                Pane header = (Pane) table.lookup("TableHeaderRow");
                if (header.isVisible()){
                    header.setMaxHeight(0);
                    header.setMinHeight(0);
                    header.setPrefHeight(0);
                    header.setVisible(false);
                }
            }
        });
    }

    @Getter @Setter @AllArgsConstructor
    public class Depth {
        private String bidSize, askSize;
        private double bidPrice, askPrice, spread;
    }

}
