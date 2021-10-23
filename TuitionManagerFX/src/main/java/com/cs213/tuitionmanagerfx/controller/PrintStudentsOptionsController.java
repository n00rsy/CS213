package com.cs213.tuitionmanagerfx.controller;

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
        SceneManager.switchScene("/com/cs213/tuitionmanagerfx/main-view.fxml",
                PrintStudentsOptionsController.class,
                (Stage) ((Node) event.getSource()).getScene().getWindow(),
                output);
    }

    @FXML
    private void handleSubmitButtonClick(ActionEvent event) {
        try {
            String currentPrintType = ((RadioButton) printType.getSelectedToggle()).getId();

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/cs213/tuitionmanagerfx/main-view.fxml"
                    )
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            MainController mainController = loader.getController();
            switch (currentPrintType) {
                case "unordered":
                    mainController.showRosterUnordered(stage);
                    break;
                case "name":
                    mainController.printRosterByStudentName();
                    break;
                case "payment":
                    mainController.printRosterByPaymentDate();
                    break;
            }

        } catch (Exception e) {
            output.setText(e.toString());
        }
    }
}
