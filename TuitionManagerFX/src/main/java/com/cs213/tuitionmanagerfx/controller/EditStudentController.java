package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.model.backend.student.Student;
import com.cs213.tuitionmanagerfx.model.enums.Major;
import com.cs213.tuitionmanagerfx.model.util.ParseUtil;
import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditStudentController {

    @FXML
    TextArea output;

    @FXML
    TextField name;

    @FXML
    ToggleGroup major;

    @FXML
    ToggleGroup studyAbroad;

    @FXML
    TextField financialAid;

    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }

    @FXML
    public void handleSubmitButtonClick(ActionEvent event) {

        try {
            String inputName = ParseUtil.parseName(name.getText());
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) major.getSelectedToggle()).getText());
            Student student = new Student(inputName, inputMajor);

            RadioButton studyAbroadToggle = (RadioButton) studyAbroad.getSelectedToggle();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
            MainController mainController = SceneManager.getMainLoader().getController();

            if (studyAbroadToggle != null) {
                mainController.setStudyAbroad(student, ParseUtil.parseBoolean(studyAbroadToggle.getText()));
            } else {
                try {
                    double financialAidAmount = Double.parseDouble(financialAid.getText());
                    mainController.setFinancialAid(student, financialAidAmount);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Please submit a valid change to the student.");
                }
            }
            stage.show();
        } catch (Exception e) {
            output.setText(e.getLocalizedMessage());
        }
    }
}
