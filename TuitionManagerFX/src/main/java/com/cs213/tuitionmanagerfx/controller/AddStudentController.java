package com.cs213.tuitionmanagerfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddStudentController {

    @FXML
    TextArea output;

    @FXML
    private void handleBackButtonClick(ActionEvent event) {
        System.out.println("back clicked!!!");

        try {
            FXMLLoader loader = new FXMLLoader(AddStudentController.class.getResource("/com/cs213/tuitionmanagerfx/main-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 1110, 750);
            stage.setScene(scene);
            MainController adc = loader.getController();
            stage.show();
        }
        catch (Exception e) {
            System.out.println(e);
            output.setText(e.toString());
        }

    }

}
