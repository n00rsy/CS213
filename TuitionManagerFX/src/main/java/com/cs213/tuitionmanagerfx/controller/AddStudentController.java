package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.implementation.backend.student.*;
import com.cs213.tuitionmanagerfx.implementation.enums.Location;
import com.cs213.tuitionmanagerfx.implementation.enums.Major;
import com.cs213.tuitionmanagerfx.implementation.util.ParseUtil;
import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML
    TextArea output;

    @FXML
    ToggleGroup studentType;

    @FXML
    ToggleGroup major;

    @FXML
    TextField name;

    @FXML
    TextField numCredits;

    @FXML
    HBox studyAbroadContainer;

    @FXML
    ToggleGroup studyAbroad;

    @FXML
    HBox locationContainer;

    @FXML
    ToggleGroup location;

    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        SceneManager.switchScene("/com/cs213/tuitionmanagerfx/main-view.fxml",
                AddStudentController.class,
                (Stage) ((Node)event.getSource()).getScene().getWindow(),
                output);
    }

    @FXML
    private void handleSubmitButtonClick(ActionEvent event) {
        Student student;
        try {
            String inputName = name.getText();
            String inputType = ((RadioButton) studentType.getSelectedToggle()).getText();
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) major.getSelectedToggle()).getText());
            int inputNumCredits = ParseUtil.parseNumCredits(numCredits.getText());
            switch (inputType) {
                case "International":
                    boolean inputStudyAbroad = ParseUtil.parseBoolean(((RadioButton) studyAbroad.getSelectedToggle()).getText());
                    student = new International(inputName,
                            inputMajor,
                            inputNumCredits,
                            inputStudyAbroad);
                    break;

                case "Resident":
                    student = new Resident(inputName, inputMajor, inputNumCredits);
                    break;

                case "Non-Resident":
                    student = new NonResident(inputName, inputMajor, inputNumCredits);
                    break;

                case "Tri-State":
                    Location inputLocation = ParseUtil.parseLocation(((RadioButton) location.getSelectedToggle()).getText());
                    student = new TriState(inputName, inputMajor, inputNumCredits, inputLocation);
                    break;
                default:
                    student = new Student("test", Major.CS);
            }
            System.out.println(student.toString());
        }
        catch (Exception e) {
            output.setText(e.toString());
        }
    }

    @FXML
    private void handleStudentTypeChange(ActionEvent event) {
        RadioButton radioButton = (RadioButton)event.getSource();
        if (radioButton.getText().equals("International")) {
            studyAbroadContainer.setDisable(false);
        }
        else {
            studyAbroadContainer.setDisable(true);
            studyAbroad.selectToggle(null);
        }

        if (radioButton.getText().equals("Tri-State")) {
            locationContainer.setDisable(false);
        }
        else {
            locationContainer.setDisable(true);
            location.selectToggle(null);
        }
    }

}
