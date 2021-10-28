package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.model.backend.Date;
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

/**
 * Controller class for pay-tuition.fxml
 * Handles tuition payment
 *
 * @author Noor, Umar
 */
public class PayTuitionController {

    @FXML
    TextArea output;

    @FXML
    TextField name;

    @FXML
    ToggleGroup major;

    @FXML
    TextField amount;

    @FXML
    TextField date;

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
     * Handles the submit button click event and attempts to submit a payment.
     *
     * @param event
     */
    @FXML
    private void handleSubmitButtonClick(ActionEvent event) {
        try {

            if (date.getText().trim().length() == 0) {
                throw new IllegalArgumentException("Please enter a payment date.");
            }
            if (amount.getText().trim().length() == 0) {
                throw new IllegalArgumentException("Please enter a payment amount.");
            }

            String inputName = ParseUtil.parseName(name.getText());
            Major inputMajor = ParseUtil.parseMajor(((RadioButton) major.getSelectedToggle()).getText());
            Date paymentDate = new Date(date.getText());
            double paymentAmount = Double.parseDouble(amount.getText());

            Student student = new Student(inputName, inputMajor);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
            MainController mainController = SceneManager.getMainLoader().getController();
            mainController.payTuition(student, paymentAmount, paymentDate);
            stage.show();
        } catch (Exception e) {
            output.setText(e.getLocalizedMessage());
        }
    }
}
