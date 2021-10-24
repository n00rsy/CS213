package com.cs213.tuitionmanagerfx.controller;

import com.cs213.tuitionmanagerfx.Application;
import com.cs213.tuitionmanagerfx.implementation.backend.student.Student;
import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class PrintStudentsOptionsController {

    @FXML
    TextArea output;

    @FXML
    ToggleGroup printType;

    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }

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
            output.setText(e.toString());
        }
    }
}
