package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.pizza.Deluxe;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CustomizationViewController {

    Pizza currentPizza;

    @FXML
    Label title;

    public void initDeluxePizza() {
        currentPizza = new Deluxe();
        title.setText("Deluxe Pizza");
    }

    public void addPizza() {

    }

}
