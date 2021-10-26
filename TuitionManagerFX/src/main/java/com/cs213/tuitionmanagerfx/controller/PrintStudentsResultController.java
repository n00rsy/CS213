package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.model.backend.student.Student;
import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * A controller class managing print-students-result.fxml
 *
 * @author Noor, Umar
 */
public class PrintStudentsResultController {

    @FXML
    TextArea output;

    @FXML
    ListView studentView;

    /**
     * Handles the back button click event and returns to the main menu.
     *
     * @param event
     */
    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }

    /**
     * Displays the students passed in using the studentView listView.
     *
     * @param students Student[] of students to display
     */
    public void setStudents(Student[] students) {
        String[] studentStrings = new String[students.length];
        for (int i = 0; i < students.length; i++) {
            studentStrings[i] = students[i].toString();
        }
        studentView.setItems(FXCollections.observableArrayList(studentStrings));
    }
}
