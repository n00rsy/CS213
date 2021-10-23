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
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    ToggleGroup locationGroup;

    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        SceneManager.switchScene("/com/cs213/tuitionmanagerfx/main-view.fxml",
                AddStudentController.class,
                (Stage) ((Node) event.getSource()).getScene().getWindow(),
                output);
    }

    @FXML
    private void handleSubmitButtonClick(ActionEvent event) {
        try {
            Student student;
            String inputName = ParseUtil.parseName(name.getText());
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
                    Location inputLocation = ParseUtil.parseLocation(((RadioButton) locationGroup.getSelectedToggle()).getText());
                    student = new TriState(inputName, inputMajor, inputNumCredits, inputLocation);
                    break;
                default:
                    throw new IllegalArgumentException("Please select a student type.");
            }
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/cs213/tuitionmanagerfx/main-view.fxml"
                    )
            );
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            MainController mainController = loader.getController();
            mainController.addStudent(student);
            stage.show();
        } catch (Exception e) {
            output.setText(e.toString());
        }
    }

    @FXML
    private void handleStudentTypeChange(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        if (radioButton.getText().equals("International")) {
            studyAbroadContainer.setDisable(false);
        } else {
            studyAbroadContainer.setDisable(true);
            studyAbroad.selectToggle(null);
        }

        if (radioButton.getText().equals("Tri-State")) {
            locationContainer.setDisable(false);
        } else {
            locationContainer.setDisable(true);
            locationGroup.selectToggle(null);
        }
    }

}
