/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection.koppeling;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Bjork
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TreeView mainTree;

    @FXML
    TableView mainTable;

    @FXML
    Button addPerson;

    @FXML
    TextField addName;

    @FXML
    TextField addDepartment;

    @FXML
    AnchorPane mainPane;

    final ObservableList<Employee> employees = FXCollections.observableArrayList(new Employee(
            "Jacob Smith", "Accounts Department"), new Employee("Isabella Johnson",
                    "Accounts Department"), new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"), new Employee("Gregory Smith",
                    "IT Support"));
    /*List<Employee> employees = Arrays.<Employee>asList(new Employee(
     "Jacob Smith", "Accounts Department"), new Employee("Isabella Johnson",
     "Accounts Department"), new Employee("Mike Graham", "IT Support"),
     new Employee("Judy Mayer", "IT Support"), new Employee("Gregory Smith",
     "IT Support"));*/
    TreeItem<String> rootNode;

    TextFieldTreeCellImpl customField = new TextFieldTreeCellImpl();

    @FXML
    private void addNewPerson(ActionEvent event) {
        addEmployeeToList(new Employee(addName.getText(), addDepartment.getText()));
        for (Employee employee : employees) {
             System.out.println(employee.getName());
        }
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TreeView
        this.rootNode = new TreeItem<>("MyCompany Human Resources");

        rootNode.setExpanded(true);
        for (Employee employee : employees) {
            addEmployeeToList(employee);
        }

        TreeView<String> treeView = new TreeView<>(rootNode);
        treeView.setEditable(true);
        treeView.setCellFactory((TreeView<String> p) -> new TextFieldTreeCellImpl());

        mainPane.getChildren().add(treeView);

        // TableView
        mainTable.setEditable(true);
        
        TableColumn nameCol = new TableColumn("Naam");
        nameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        ((Employee) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setName(t.getNewValue());
                    }
                }
        );

        TableColumn departmentCol = new TableColumn("Afdeling");
        departmentCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));
        departmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        departmentCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Employee, String>>() {
                    @Override
                    public void handle(CellEditEvent<Employee, String> t) {
                        ((Employee) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDepartment(t.getNewValue());
                    }
                }
        );

        mainTable.getColumns().addAll(nameCol, departmentCol);
        mainTable.setItems(employees);
    }

    private void addEmployeeToList(Employee employee) {
        TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
        boolean found = false;
        for (TreeItem<String> depNode : rootNode.getChildren()) {
            if (depNode.getValue().contentEquals(employee.getDepartment())) {
                depNode.getChildren().add(empLeaf);
                found = true;
                break;
            }
        }
        if (!found) {
            TreeItem<String> depNode = new TreeItem<>(employee.getDepartment());
            rootNode.getChildren().add(depNode);
            depNode.getChildren().add(empLeaf);
        }
    }
}
