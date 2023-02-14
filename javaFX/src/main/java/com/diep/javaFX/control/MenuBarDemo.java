package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuBarDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Demo");
        HBox hbox = new HBox(createMenuBar());

        Scene scene = new Scene(hbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        menuBar.getMenus().add(createSimpleMenu());
        menuBar.getMenus().add(createCheckMenu());
        menuBar.getMenus().add(createRadioMenu());
        menuBar.getMenus().add(createMenuWithSeparator());
        menuBar.getMenus().add(createCustomMenu());

        return menuBar;
    }

    private Menu createSimpleMenu() {
        Menu menu = new Menu("Simple");

        // Add action listener to menu
        menu.setOnShowing(e -> System.out.println("Showing Menu 1"));
        menu.setOnShown  (e -> System.out.println("Shown Menu 1"));
        menu.setOnHiding (e -> System.out.println("Hiding Menu 1"));
        menu.setOnHidden (e -> System.out.println("Hidden Menu 1"));

        // Add menu items to menu
        MenuItem menuItem1 = new MenuItem("Item 1");
        MenuItem menuItem2 = new MenuItem("Item 2");
        menu.getItems().addAll(menuItem1, menuItem2);

        // Add action listener to menu items
        menuItem1.setOnAction(e -> System.out.println("Menu Item 1 Selected"));
        menuItem2.setOnAction(e -> System.out.println("Menu Item 2 Selected"));

        // Add a menu with submenus
        menu.getItems().add(createMenuWithSubMenu());

        return menu;
    }

    private Menu createMenuWithSubMenu() {
        Menu menuItem3 = new Menu("Item 3");

        // Add subMenu to menuItem3
        MenuItem subMenu = new MenuItem("Item 3.1");
        menuItem3.getItems().add(subMenu);

        return menuItem3;
    }

    private Menu createCheckMenu() {
        Menu menu = new Menu("Check");
        CheckMenuItem checkMenuItem = new CheckMenuItem("Check this!");
        CheckMenuItem checkMenuItem2 = new CheckMenuItem("Check that!");
        menu.getItems().addAll(checkMenuItem, checkMenuItem2);

        return menu;
    }

    private Menu createRadioMenu() {
        Menu menu = new Menu("Radio");
        RadioMenuItem choice1Item = new RadioMenuItem("Choice 1");
        RadioMenuItem choice2Item = new RadioMenuItem("Choice 2");
        RadioMenuItem choice3Item = new RadioMenuItem("Choice 3");

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(choice1Item);
        toggleGroup.getToggles().add(choice2Item);
        toggleGroup.getToggles().add(choice3Item);

        menu.getItems().add(choice1Item);
        menu.getItems().add(choice2Item);
        menu.getItems().add(choice3Item);

        return menu;
    }

    private Menu createMenuWithSeparator() {
        Menu menu = new Menu("Separator");
        MenuItem item1 = new MenuItem("Item 1");
        MenuItem item2 = new MenuItem("Item 2");
        SeparatorMenuItem separator = new SeparatorMenuItem();

        menu.getItems().add(item1);
        menu.getItems().add(separator);
        menu.getItems().add(item2);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        return menu;
    }

    private Menu createCustomMenu() {
        Menu menu = new Menu("Custom");

        Slider slider = new Slider(0, 100, 50);

        CustomMenuItem customMenuItem = new CustomMenuItem();
        customMenuItem.setContent(slider);
        customMenuItem.setHideOnClick(false);
        menu.getItems().add(customMenuItem);

        Button button = new Button("Custom Menu Item Button");
        CustomMenuItem customMenuItem2 = new CustomMenuItem();
        customMenuItem2.setContent(button);
        // Keep menu open while user interacts with this custom item
        customMenuItem2.setHideOnClick(false);
        menu.getItems().add(customMenuItem2);

        return menu;
    }
}
