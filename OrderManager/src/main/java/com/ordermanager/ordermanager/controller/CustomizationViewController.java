package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.model.pizza.PizzaMaker;
import com.ordermanager.ordermanager.model.pizza.Topping;
import com.ordermanager.ordermanager.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CustomizationViewController {

    Pizza currentPizza;
    ArrayList<Topping> defaultToppings;

    @FXML
    Label title;

    @FXML
    ImageView pizzaImageView;

    @FXML
    VBox presetToppingsContainer;

    @FXML
    VBox additionalToppingsContainer;

    public void initDeluxePizza() {
        title.setText("Add Deluxe Pizza");
        setPizzaImage("deluxe.png");
        currentPizza = PizzaMaker.createPizza(Constants.DELUXE);
        defaultToppings = currentPizza.getToppings();
        
    }

    public void toppingChanged(ActionEvent event) {
        System.out.println("its lit");
    }

    private void setPizzaImage(String path) {
        try {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
            pizzaImageView.setImage(image);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
