package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.Order;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CurrentOrderController {

    Order currentOrder;

    @FXML
    TextField phoneNumber;

    @FXML
    TableView pizzaTableView;

    @FXML
    Text totalText;

    @FXML
    Button submitButton;

    public void initialize() {
        pizzaTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            System.out.println("ListView selection changed from oldValue = " + oldValue + " to newValue = " + newValue);
        });
    }

    public void setCurrentOrder(Order order) {
        currentOrder = order;
        phoneNumber.setText(currentOrder.getPhoneNumber());
        pizzaTableView.setItems(FXCollections.observableArrayList(currentOrder.getPizzas()));
    }

    public void handleSubmitButtonClick(ActionEvent event) {

        currentOrder.setPhoneNumber(phoneNumber.getText());

        if (currentOrder.isValid()) {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
            MainMenuController mainMenuController = SceneManager.getMainLoader().getController();
            mainMenuController.completeCurrentOrder();
            stage.show();
        }
        else {
            SceneManager.showErrorAlert("Please submit an order with one or more pizzas and a valid phone number.");
        }
    }

}
