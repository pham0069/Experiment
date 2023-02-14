package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuButtonDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MenuButton Demo");
        HBox hbox = new HBox(createMenu());

        Scene scene = new Scene(hbox, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuButton createMenu() {
        MenuItem menuItem1 = new MenuItem("Option 1");
        MenuItem menuItem2 = new MenuItem("Option 2");
        MenuItem menuItem3 = new MenuItem("Option 3");
        menuItem3.setOnAction(e -> System.out.println("Option 3 selected"));

        MenuButton menuButton = new MenuButton("Options", null, menuItem1, menuItem2, menuItem3);
        return menuButton;
    }

    /**
     *
     * @return
     */
    private SplitMenuButton createSplitMenu() {
        MenuItem choice1 = new MenuItem("Choice 1");
        MenuItem choice2 = new MenuItem("Choice 2");
        MenuItem choice3 = new MenuItem("Choice 3");

        choice1.setOnAction((e)-> {
            System.out.println("Choice 1 selected");
        });
        choice2.setOnAction((e)-> {
            System.out.println("Choice 2 selected");
        });
        choice3.setOnAction((e)-> {
            System.out.println("Choice 3 selected");
        });

        SplitMenuButton splitMenuButton = new SplitMenuButton();
        splitMenuButton.setText("Click here");
        splitMenuButton.setOnAction(e -> System.out.println("Primary action clicked"));
        splitMenuButton.getItems().addAll(choice1, choice2, choice3);
        return splitMenuButton;
    }

}
