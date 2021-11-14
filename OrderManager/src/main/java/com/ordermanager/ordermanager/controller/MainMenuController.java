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

/**
 * The controller class for main-menu-view.fxml
 *
 * @author Noor, Umar
 */
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

    /**
     * Method handles event when the user clicks on the Deluxe pizza button.
     *
     * @param event
     */
    public void handleOrderDeluxePizzaClick(ActionEvent event) {
        handleOrderClick(event, Constants.DELUXE);
    }

    /**
     * Method handles event when the user clicks on the Hawaiian pizza button.
     *
     * @param event
     */
    public void handleOrderHawaiianPizzaClick(ActionEvent event) {
        handleOrderClick(event, Constants.HAWAIIAN);
    }

    /**
     * Method handles event when the user clicks on the Pepperoni pizza button.
     *
     * @param event
     */
    public void handleOrderPepperoniPizzaClick(ActionEvent event) {
        handleOrderClick(event, Constants.PEPPERONI);
    }


    /**
     * Handles when the user orders a pizza by switching to the pizza customization scene.
     *
     * @param event
     * @param flavor The flavor of pizza the user ordered
     */
    private void handleOrderClick(ActionEvent event, String flavor) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customization-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            CustomizationController customizationController = loader.getController();
            customizationController.init(flavor);
            stage.show();
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }

    /**
     * Handles when the user clicks the current order button by switching to the corresponding view.
     *
     * @param event
     */
    public void handleCurrentOrderClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/current-order-view.fxml"));
            Scene scene = new Scene(loader.load());
            CurrentOrderController currentOrderController = loader.getController();
            currentOrderController.init(currentOrder, storeOrders);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }

    /**
     * Handles when the user clicks the store orders button by switching to the corresponding scene.
     *
     * @param event
     */
    public void handleStoreOrdersClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/store-orders-view.fxml"));
            Scene scene = new Scene(loader.load());
            StoreOrdersController storeOrdersController = loader.getController();
            storeOrdersController.init(storeOrders);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }

    /**
     * Adds the pizza to the current order.
     *
     * @param pizza the pizza to add
     */
    public void addPizzaToOrder(Pizza pizza) {
        currentOrder.addPizza(pizza);
    }

    /**
     * Add the current order to the list of orders in the store and resets the current order.
     */
    public void completeCurrentOrder() {
        storeOrders.addOrder(currentOrder);
        currentOrder = new Order();
    }
}
