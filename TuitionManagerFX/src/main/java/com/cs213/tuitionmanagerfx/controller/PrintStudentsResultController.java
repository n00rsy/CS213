package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.implementation.backend.student.Student;
import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PrintStudentsResultController {

    @FXML
    TextArea output;

    @FXML
    ListView studentView;

    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        SceneManager.switchScene("/com/cs213/tuitionmanagerfx/main-view.fxml",
                PrintStudentsResultController.class,
                (Stage) ((Node) event.getSource()).getScene().getWindow(),
                output);
    }

    public void setStudents(Student[] students) {
        System.out.println("print students result controller wiriking");
        String[] yert = new String[students.length];
        for (int i = 0; i < students.length; i++) {
            yert[i] = students[i].toString();
        }
        studentView.setItems(FXCollections.observableArrayList(yert));
    }

}
