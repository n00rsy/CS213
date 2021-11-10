package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.pizza.Deluxe;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomizationViewController {

    Pizza currentPizza;

    @FXML
    Label title;

    @FXML
    ImageView pizzaImageView;

    public void initDeluxePizza() {
        currentPizza = new Deluxe();
        title.setText("Deluxe Pizza");
        setPizzaImage("img/deluxe.png");
    }

    private void setPizzaImage(String path) {
        try {
            System.out.println(System.getProperty("user.dir"));
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
            pizzaImageView.setImage(image);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
