package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;


public class TableViewDemo extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TableView Demo");
        VBox box = new VBox(createTableView());
        Scene scene = new Scene(box, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView createTableView() {
        TableView tableView = new TableView();

        /**
         * Property for CellValueFactory must match the Person property with Set/Get method
         */
        TableColumn<String, Person> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));


        TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        tableView.getItems().add(new Person("John", "Doe"));
        tableView.getItems().add(new Person("Jane", "Deer"));
        return tableView;
    }

    /**
     * Class has to be public to be accessible
     */
    @Getter @Setter
    public class Person {
        private String firstName = null;
        private String lastName = null;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
