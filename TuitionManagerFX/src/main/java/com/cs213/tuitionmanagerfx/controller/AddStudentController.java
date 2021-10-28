package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.model.backend.student.*;
import com.cs213.tuitionmanagerfx.model.enums.Location;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A controller class managing add-student.fxml
 *
 * @author Noor, Umar
 */
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
     * Handles the back button click event and attempts to add a student to the roster. If successful, returns to the main menu.
     *
     * @param event
     */
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

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
            MainController mainController = SceneManager.getMainLoader().getController();
            mainController.addStudent(student);
            stage.show();
        } catch (Exception e) {
            output.setText(e.getLocalizedMessage());
        }
    }

    /**
     * Handles when different student types are selected, and activates/ deactivates the correct fields
     *
     * @param event
     */
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
