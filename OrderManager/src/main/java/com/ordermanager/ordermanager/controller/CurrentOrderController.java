package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.Order;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.util.Configuration;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;

public class CurrentOrderController {

    Order currentOrder;
    Pizza selectedPizza;

    @FXML
    TextField phoneNumber;

    @FXML
    TableView pizzaTableView;

    @FXML
    Text totalText;

    @FXML
    TableColumn details;

    @FXML
    TableColumn price;

    public void initialize() {
        price.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Pizza, String>, ObservableValue<String>>) p ->
            new ReadOnlyStringWrapper(String.format(Configuration.PRICE_FORMAT, p.getValue().price()))
        );

        details.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Pizza, String>, ObservableValue<String>>) p ->
                new ReadOnlyStringWrapper(p.getValue().toString())
        );

        pizzaTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Pizza>) (observable, oldValue, newValue) -> {
            System.out.println("ListView selection changed from oldValue");
        });
    }

    public void setCurrentOrder(Order order) {
        System.out.println("setting current order");
        currentOrder = order;
        phoneNumber.setText(currentOrder.getPhoneNumber());
        ArrayList<Pizza> pizzas = currentOrder.getPizzas();

        ArrayList<String> details = new ArrayList<>();
        ArrayList<Double> prices = new ArrayList<>();

        for(Pizza pizza : pizzas) {
            details.add("PIzza details here");
            prices.add(pizza.price());
        }


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
        } else {
            SceneManager.showErrorAlert("Please submit an order with one or more pizzas and a valid phone number.");
        }
    }

    @FXML
    public void handleRemovePizzaButtonClick(ActionEvent event) {
        currentOrder.removePizza(selectedPizza);
    }

    @FXML
    public void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }

}
