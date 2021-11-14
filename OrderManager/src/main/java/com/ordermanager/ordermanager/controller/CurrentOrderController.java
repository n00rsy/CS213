package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.Order;
import com.ordermanager.ordermanager.model.StoreOrders;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.util.Configuration;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Controller class for current-order-view.fxml
 *
 * @author Noor, Umar
 */
public class CurrentOrderController {

    @FXML
    TextField phoneNumber;
    @FXML
    TableView pizzaTableView;
    @FXML
    Text totalText;
    @FXML
    Text taxText;
    @FXML
    TableColumn details;
    @FXML
    TableColumn price;
    private StoreOrders storeOrders;
    private Order currentOrder;
    private Pizza selectedPizza;

    /**
     * Initializes the scene.
     * Called automatically by JavaFX.
     */
    public void initialize() {
        price.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Pizza, String>, ObservableValue<String>>) p ->
                new ReadOnlyStringWrapper("$" + String.format(Configuration.PRICE_FORMAT, p.getValue().price()))
        );

        details.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Pizza, String>, ObservableValue<String>>) p ->
                new ReadOnlyStringWrapper(p.getValue().toString())
        );

        pizzaTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Pizza>) (observable, oldValue, newValue) -> {
            selectedPizza = newValue;
        });
    }

    /**
     * Initializes the scene using custom parameters.
     *
     * @param order the current order to edit
     * @param storeOrders   All orders in the system.
     */
    public void init(Order order, StoreOrders storeOrders) {
        System.out.println("setting current order");
        currentOrder = order;
        this.storeOrders = storeOrders;
        phoneNumber.setText(currentOrder.getPhoneNumber());
        currentOrderChanged();
    }

    /**
     * Updates the UI when the order is changed.
     */
    private void currentOrderChanged() {
        pizzaTableView.getItems().clear();
        pizzaTableView.setItems(FXCollections.observableArrayList(currentOrder.getPizzas()));
        double price = currentOrder.price();
        double tax = currentOrder.tax();
        taxText.setText("Sales Tax: $" + String.format(Configuration.PRICE_FORMAT, tax));
        totalText.setText("Order Total: $" + String.format(Configuration.PRICE_FORMAT, price + tax));
    }

    /**
     * Handles the submit button click by adding the current order to storeOrders and switching back to the main menu.
     *
     * @param event
     */
    public void handleSubmitButtonClick(ActionEvent event) {

        currentOrder.setPhoneNumber(phoneNumber.getText());

        if (currentOrder.isValid() && !storeOrders.contains(phoneNumber.getText())) {

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(SceneManager.getMainScene());
            MainMenuController mainMenuController = SceneManager.getMainLoader().getController();
            mainMenuController.completeCurrentOrder();
            stage.show();
        } else {
            SceneManager.showErrorAlert("Please submit an order with one or more pizzas and a valid phone number.");
        }
    }

    /**
     * Removes a pizza from the order.
     *
     * @param event
     */
    @FXML
    public void handleRemovePizzaButtonClick(ActionEvent event) {
        if (selectedPizza == null) {
            SceneManager.showErrorAlert("Please select a pizza to remove.");
        } else {
            currentOrder.removePizza(selectedPizza);
            currentOrderChanged();
        }
    }

    /**
     * Handles the back button click event by returning to the main menu scene.
     * @param event
     */
    @FXML
    public void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }
}
