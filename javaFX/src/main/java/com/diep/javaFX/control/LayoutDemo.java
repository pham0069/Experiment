package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LayoutDemo  extends Application {
    private final Button button1 = new Button("Button Number 1");
    private final Button button2 = new Button("Button Number 2");
    private final Button button3 = new Button("Button Number 3");
    private final Button button4 = new Button("Button Number 4");
    private final Button button5 = new Button("Button Number 5");
    private final Button button6 = new Button("Button Number 6");

    private final Separator separator = new Separator(Orientation.HORIZONTAL);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Box Demo");
        Scene scene = new Scene(createParent(Layout.FLOW));

        primaryStage.setScene(scene);
        primaryStage.setWidth(400);
        primaryStage.setHeight(200);
        primaryStage.show();
    }

    private Parent createParent(Layout layout) {
        switch (layout){
            case VBOX:
                return createVBox();
            case HBOX:
                return createHBox();
            case FLOW:
                return createFlowPane();
            case TILE:
                return createTilePane();
            case GRID:
                return createGridPaneWithSpanning();
            default:
                return createGroup();
        }

    }
    private Group createGroup() {
        Group group = new Group(button1, button2);
        //group.getChildren().addAll(button1, button2);
        return group;
    }

    private HBox createHBox() {
        HBox box = new HBox(50, button1, button2);
        box.setSpacing(20);
        return box;
    }

    private VBox createVBox() {
        VBox box = new VBox(10, button1, separator, button2);
        return box;
    }

    /**
     * By default the components in a FlowPane are laid out horizontally, wrapping onto the next horizontal line when
     * there is no longer space enough inside the FlowPane to show more components horizontally.
     * However, we also can set the orientation of flow pane
     * @return
     */
    private FlowPane createFlowPane() {
        FlowPane flow = new FlowPane();
        flow.getChildren().addAll(button1, button2, button3);
        flow.setHgap(10);
        flow.setVgap(10);
        //flow.setOrientation(Orientation.VERTICAL);
        return flow;
    }

    /**
     * Layout component which lays out its child component in a grid of equally sized cell
     * @return
     */
    private TilePane createTilePane() {
        TilePane tilePane = new TilePane();

        Button[] buttons = new Button[]{
                new Button("Button 1"),
                new Button("Button Number 2"),
                new Button("Button No 3"),
                new Button("Button No 4"),
                new Button("Button 5"),
                new Button("Button Number 6")
        };

        tilePane.getChildren().addAll(buttons);
        tilePane.setTileAlignment(Pos.TOP_LEFT);
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        return tilePane;
    }

    /**
     * Lay out its child components in a grid. The size of the cells in the grid depends on the components displayed in
     * the GridPane
     * Different from the TilePane in that a GridPane allows different size of cells, whereas a TilePane makes all tiles
     * the same size.
     * @return
     */
    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.add(button1, 0, 0, 1, 1);
        gridPane.add(button2, 1, 0, 1, 1);
        gridPane.add(button3, 2, 0, 1, 1);
        gridPane.add(button4, 0, 1, 1, 1);
        gridPane.add(button5, 1, 1, 1, 1);
        gridPane.add(button6, 2, 1, 1, 1);

        return gridPane;
    }

    private GridPane createGridPaneWithSpanning() {
        GridPane gridPane = new GridPane();

        gridPane.add(button1, 0, 0, 2, 2);

        gridPane.add(button2, 2, 0, 1, 1);
        gridPane.add(button3, 2, 1, 1, 1);
        gridPane.add(button4, 0, 2, 1, 1);
        gridPane.add(button5, 1, 2, 1, 1);
        gridPane.add(button6, 2, 2, 1, 1);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        return gridPane;
    }

    enum Layout {
        GROUP,
        HBOX,
        VBOX,
        FLOW,
        TILE,
        GRID
    }
}
