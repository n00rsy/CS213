package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.Order;
import com.ordermanager.ordermanager.model.StoreOrders;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.util.Constants;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    private StoreOrders storeOrders;
    private Order currentOrder;

    /**
     * Runs on load and initializes the roster.
     */
    public void initialize() {
        storeOrders = new StoreOrders();
        currentOrder = new Order();
    }

    public void handleOrderDeluxePizzaClick(ActionEvent event) {
        handleOrderClick(event, Constants.DELUXE);
    }

    public void handleOrderHawaiianPizzaClick(ActionEvent event) {
        handleOrderClick(event, Constants.HAWAIIAN);
    }

    public void handleOrderPepperoniPizzaClick(ActionEvent event) {
        handleOrderClick(event, Constants.PEPPERONI);
    }

    private void handleOrderClick(ActionEvent event, String flavor) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customization-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            CustomizationViewController customizationViewController = loader.getController();
            customizationViewController.init(flavor);
            stage.show();
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }

    public void handleCurrentOrderClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/current-order-view.fxml"));
            Scene scene = new Scene(loader.load());
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.setCurrentOrder(currentOrder);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }

    public void addPizzaToOrder(Pizza pizza) {
        currentOrder.addPizza(pizza);
    }

    public void completeCurrentOrder() {
        storeOrders.addOrder(currentOrder);
        currentOrder = new Order();
    }

    public void removePizzaFromOrder(Pizza pizza) {
        currentOrder.removePizza(pizza);
    }


}
