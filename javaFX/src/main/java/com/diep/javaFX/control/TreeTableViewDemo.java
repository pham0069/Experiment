package com.diep.javaFX.control;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TreeTableViewDemo extends Application {
    private List<Employee> subordinates = Arrays.<Employee>asList(
            new Employee("Ethan Williams", "ethan.williams@example.com"),
            new Employee("Emma Jones", "emma.jones@example.com"),
            new Employee("Michael Brown", "michael.brown@example.com"),
            new Employee("Anna Black", "anna.black@example.com"),
            new Employee("Rodger York", "roger.york@example.com"),
            new Employee("Susan Collins", "susan.collins@example.com"));
    private List<Employee> bosses = Arrays.asList(
            new Employee("Zoo Lion", "zoo.lion@example.com"),
            new Employee("Jungle Book", "jungle.book@example.com"));

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TreeTableView Demo");
        VBox box = new VBox(createTreeTableView());
        Scene scene = new Scene(box, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Combination of both tree view and table view
     * @return
     */
    private TreeTableView createTreeTableView() {
        TreeTableColumn<Employee, String> nameColumn = createColumn("Employee", Employee::getName);
        TreeTableColumn<Employee, String> emailColumn = createColumn("Email", Employee::getEmail);

        TreeTableView<Employee> treeTableView = new TreeTableView<>();
        treeTableView.getColumns().setAll(nameColumn, emailColumn);
        addTreeItems(treeTableView);

        return treeTableView;
    }

    private void addTreeItems(TreeTableView treeTableView) {
        TreeItem<Employee> root = new TreeItem<>(new Employee("Tom King", "tom.king@example.com"));
        for (Employee boss : bosses) {
            TreeItem<Employee> parent = new TreeItem(boss);
            parent.getChildren().addAll(subordinates.stream().map(s -> new TreeItem<>(s)).collect(Collectors.toList()));
            root.getChildren().add(parent);
        }
        treeTableView.setRoot(root);
    }

    private TreeTableColumn<Employee, String> createColumn(String text, Function<Employee, String> extractFunction) {
        TreeTableColumn<Employee, String> nameColumn = new TreeTableColumn<>(text);
        nameColumn.setPrefWidth(150);
        nameColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(extractFunction.apply(param.getValue().getValue()))
        );

        return nameColumn;
    }

    public class Employee {
        private SimpleStringProperty name;
        private SimpleStringProperty email;
        public SimpleStringProperty nameProperty() {
            if (name == null) {
                name = new SimpleStringProperty(this, "name");
            }
            return name;
        }
        public SimpleStringProperty emailProperty() {
            if (email == null) {
                email = new SimpleStringProperty(this, "email");
            }
            return email;
        }
        private Employee(String name, String email) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
        }
        public String getName() {
            return name.get();
        }
        public void setName(String fName) {
            name.set(fName);
        }
        public String getEmail() {
            return email.get();
        }
        public void setEmail(String fName) {
            email.set(fName);
        }
    }

}
