package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.model.pizza.PizzaMaker;
import com.ordermanager.ordermanager.model.pizza.Size;
import com.ordermanager.ordermanager.model.pizza.Topping;
import com.ordermanager.ordermanager.util.Configuration;
import com.ordermanager.ordermanager.util.Constants;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomizationViewController {

    Pizza currentPizza;
    ArrayList<CheckBox> defaultToppings = new ArrayList<>();
    ArrayList<CheckBox> additionalToppings = new ArrayList<>();

    @FXML
    Label title;

    @FXML
    ImageView pizzaImageView;

    @FXML
    VBox presetToppingsContainer;

    @FXML
    VBox additionalToppingsContainer;

    @FXML
    Text priceText;

    @FXML
    ToggleGroup size;

    public void init(String flavor) {
        switch (flavor) {
            case Constants.DELUXE -> init("Add Deluxe Pizza", "deluxe.png", Constants.DELUXE);
            case Constants.HAWAIIAN -> init("Add Hawaiian Pizza", "hawaiian.png", Constants.HAWAIIAN);
            case Constants.PEPPERONI -> init("Add Pepperoni Pizza", "pepperoni.jpeg", Constants.PEPPERONI);
            default -> throw new IllegalArgumentException("Illegal pizza flavor!");
        }
    }

    private void init(String name, String imgPath, String flavor) {
        title.setText(name);
        setPizzaImage(imgPath);
        currentPizza = PizzaMaker.createPizza(flavor);
        createToppingCheckboxes(presetToppingsContainer, defaultToppings, currentPizza.getToppings(), true);
        createToppingCheckboxes(additionalToppingsContainer, additionalToppings, Arrays.stream(Topping.values()).filter(t -> !currentPizza.getToppings().contains(t)).collect(Collectors.toList()), false);
        pizzaChanged();
    }

    private void createToppingCheckboxes(Pane container, List<CheckBox> checkboxes, List<Topping> toppings, boolean selected) {
        for (Topping topping : toppings) {
            CheckBox checkbox = new CheckBox(topping.toString());
            checkbox.setOnAction(e -> pizzaChanged());
            checkbox.setSelected(selected);
            checkboxes.add(checkbox);
        }
        container.getChildren().addAll(checkboxes);
    }

    public void pizzaChanged() {

        ArrayList<Topping> newToppings = new ArrayList<>();
        for (CheckBox checkBox : defaultToppings) {
            if (checkBox.isSelected()) {
                newToppings.add(Topping.valueOf(checkBox.getText()));
            }
        }
        for (CheckBox checkBox : additionalToppings) {
            if (checkBox.isSelected()) {
                newToppings.add(Topping.valueOf(checkBox.getText()));
            }
        }

        // TODO: make sure 7 toppings max

        currentPizza.setSize(Size.valueOf(((RadioButton) size.getSelectedToggle()).getText()));
        currentPizza.setToppings(newToppings);
        priceText.setText("Price: $" + String.format(Configuration.PRICE_FORMAT, currentPizza.price()));
    }

    private void setPizzaImage(String path) {
        try {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
            pizzaImageView.setImage(image);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }

    @FXML
    public void handlePlaceOrderButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        MainMenuController mainMenuController = SceneManager.getMainLoader().getController();
        mainMenuController.addPizzaToOrder(currentPizza);
        stage.show();
    }
}
