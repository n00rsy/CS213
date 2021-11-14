package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.model.pizza.PizzaMaker;
import com.ordermanager.ordermanager.model.pizza.enums.Size;
import com.ordermanager.ordermanager.model.pizza.enums.Topping;
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

/**
 * The controller class associated with customization-view.fxml
 *
 * @author Noor, Umar
 */
public class CustomizationController {

    private Pizza currentPizza;
    private ArrayList<CheckBox> defaultToppings = new ArrayList<>();
    private ArrayList<CheckBox> additionalToppings = new ArrayList<>();

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

    /**
     * Sets up the scene with the correct flavor.
     *
     * @param flavor The flavor to initiate the customization menu with.
     */
    public void init(String flavor) {
        switch (flavor) {
            case Constants.DELUXE -> init("Add Deluxe Pizza", "deluxe.png", Constants.DELUXE);
            case Constants.HAWAIIAN -> init("Add Hawaiian Pizza", "hawaiian.png", Constants.HAWAIIAN);
            case Constants.PEPPERONI -> init("Add Pepperoni Pizza", "pepperoni.jpeg", Constants.PEPPERONI);
            default -> throw new IllegalArgumentException("Illegal pizza flavor!");
        }
    }

    /**
     * Sets up the scene using the provided inputs.
     *
     * @param titleText  The text to display as the scene title.
     * @param imgPath   The path to the image of the pizza
     * @param flavor    The string representation of the pizza flavor.
     */
    private void init(String titleText, String imgPath, String flavor) {
        title.setText(titleText);
        setPizzaImage(imgPath);
        currentPizza = PizzaMaker.createPizza(flavor);
        createToppingCheckboxes(presetToppingsContainer, defaultToppings, currentPizza.getToppings(), true);
        createToppingCheckboxes(additionalToppingsContainer, additionalToppings, Arrays.stream(Topping.values()).filter(t -> !currentPizza.getToppings().contains(t)).collect(Collectors.toList()), false);
        pizzaChanged();
    }

    /**
     * Builds up the topping checkboxes that toggle toppings on the pizza and sets their default values.
     *
     * @param container The container pane to put the checkboxes in
     * @param checkboxes    A List of CheckBox objects to store references in
     * @param toppings  A List of Topping objects to generate CheckBoxes for
     * @param selected  The default value for the checkboxes
     */
    private void createToppingCheckboxes(Pane container, List<CheckBox> checkboxes, List<Topping> toppings, boolean selected) {
        for (Topping topping : toppings) {
            CheckBox checkbox = new CheckBox(topping.toString());
            checkbox.setOnAction(e -> pizzaChanged());
            checkbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue == false && newValue == true && getSelectedToppings().size() > Constants.MAX_TOPPINGS) {
                    checkbox.setSelected(false);
                    SceneManager.showErrorAlert("Pizza cannot have more than 7 toppings.");
                } else {
                    checkbox.setSelected(newValue);
                }
            });
            checkbox.setSelected(selected);
            checkboxes.add(checkbox);
        }
        container.getChildren().addAll(checkboxes);
    }

    /**
     * Leverages the defaultToppings and additionalToppings Lists to return a list of all selected toppings.
     *
     * @return
     */
    private ArrayList<Topping> getSelectedToppings() {
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
        return newToppings;
    }

    /**
     * Updates the UI when the pizza is changed.
     */
    public void pizzaChanged() {

        ArrayList<Topping> newToppings = getSelectedToppings();

        currentPizza.setSize(Size.valueOf(((RadioButton) size.getSelectedToggle()).getText()));
        currentPizza.setToppings(newToppings);
        priceText.setText("Price: $" + String.format(Configuration.PRICE_FORMAT, currentPizza.price()));
    }

    /**
     * Sets the pizza image using the path to the image provided.
     * @param path  Path to the pizza image
     */
    private void setPizzaImage(String path) {
        try {
            Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
            pizzaImageView.setImage(image);
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }

    /**
     * Handles the place order button click by adding the current pizza to the order and switching back to the main scene.
     *
     * @param event
     */
    @FXML
    public void handlePlaceOrderButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        MainMenuController mainMenuController = SceneManager.getMainLoader().getController();
        mainMenuController.addPizzaToOrder(currentPizza);
        stage.show();
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
