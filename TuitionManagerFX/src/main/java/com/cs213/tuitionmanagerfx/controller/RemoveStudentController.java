package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.implementation.backend.student.Student;
import com.cs213.tuitionmanagerfx.implementation.enums.Major;
import com.cs213.tuitionmanagerfx.implementation.util.ParseUtil;
import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/** A controller class managing remove-student.fxml
 * @author Noor, Umar
 */
public class RemoveStudentController {

    @FXML
    TextArea output;

    @FXML
    TextField name;

    @FXML
    ToggleGroup major;

    /**
     * Handles the back button click event and returns to the main menu.
     * @param event
     */
    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }

    /**
     * Handles the submit button click event and attempts to remove a student.
     * @param event
     */
    @FXML
    private void handleSubmitButtonClick(ActionEvent event) {
        try {
            String inputName = ParseUtil.parseName(name.getText());
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) major.getSelectedToggle()).getText());
            Student student = new Student(inputName, inputMajor);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
            MainController mainController = SceneManager.getMainLoader().getController();
            mainController.removeStudent(student);
            stage.show();
        } catch (Exception e) {
            output.setText(e.toString());
        }
    }
}
