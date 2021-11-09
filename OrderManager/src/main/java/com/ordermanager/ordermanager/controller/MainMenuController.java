package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.Order;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainMenuController {

    private ArrayList<Order> orders;
    private Order currentOrder;

    /**
     * Runs on load and initializes the roster.
     */
    public void initialize() {
        orders = new ArrayList<>();
        currentOrder = new Order();
    }


    public void HandleOrderDeluxePizzaClick(ActionEvent event) {
        System.out.println("ordering pizza");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/customization-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            CustomizationViewController customizationViewController = loader.getController();
            customizationViewController.initDeluxePizza();
            stage.show();
        }
        catch (Exception e) {

        }
    }

    public void addPizzaToOrder(Pizza pizza) {
        currentOrder.getPizzas().add(pizza);
    }

    public void removePizzaFromOrder(Pizza pizza) {

    }


}
