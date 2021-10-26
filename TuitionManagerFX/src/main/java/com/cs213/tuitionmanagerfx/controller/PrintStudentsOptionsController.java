package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * A controller class managing print-students-options.fxml
 *
 * @author Noor, Umar
 */
public class PrintStudentsOptionsController {

    @FXML
    TextArea output;

    @FXML
    ToggleGroup printType;

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
     * Handles the submit button click event and attempts to display roster based on input.
     *
     * @param event
     */
    @FXML
    private void handleSubmitButtonClick(ActionEvent event) {
        try {
            String currentPrintType = ((RadioButton) printType.getSelectedToggle()).getId();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
            MainController mainController = SceneManager.getMainLoader().getController();

            switch (currentPrintType) {
                case "unordered":
                    mainController.showRosterUnordered(stage);
                    break;
                case "name":
                    mainController.showRosterByStudentName(stage);
                    break;
                case "payment":
                    mainController.showRosterByPaymentDate(stage);
                    break;
            }
        } catch (Exception e) {
            output.setText(e.getLocalizedMessage());
        }
    }
}
