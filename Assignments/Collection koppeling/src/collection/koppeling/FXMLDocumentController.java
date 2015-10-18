/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collection.koppeling;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

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

    List<Employee> employees = Arrays.<Employee>asList(new Employee(
            "Jacob Smith", "Accounts Department"), new Employee("Isabella Johnson",
                    "Accounts Department"), new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"), new Employee("Gregory Smith",
                    "IT Support"));
    TreeItem<String> rootNode;
    
    TextFieldTreeCellImpl customField = new TextFieldTreeCellImpl();

    @FXML
    private void addNewPerson(ActionEvent event) {
        employees.add(new Employee(addName.getText(), addDepartment.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rootNode = new TreeItem<>("MyCompany Human Resources");
        
        
        rootNode.setExpanded(true);
        for (Employee employee : employees) {
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

        
        
        mainTree.setRoot(rootNode);
        mainTree.setEditable(true);
        //mainTree.setCellFactory();
    }

}
