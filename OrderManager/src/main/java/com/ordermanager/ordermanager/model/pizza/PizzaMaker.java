package com.ordermanager.ordermanager.model.pizza;

import com.ordermanager.ordermanager.model.pizza.Deluxe;
import com.ordermanager.ordermanager.model.pizza.Hawaiian;
import com.ordermanager.ordermanager.model.pizza.Pepperoni;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.util.Constants;


/**
 * Factory class for Pizza
 *
 * @author Noor, Umar
 */
public class PizzaMaker {

    /**
     * Creates an instance of Pizza subclasses based on the chosen flavor
     * @param flavor    The flavor of the pizza
     * @return  Pizza object with correct flavor
     */
    public static Pizza createPizza(String flavor) {
        switch (flavor.toLowerCase()) {
            case Constants.DELUXE:
                return new Deluxe();
            case Constants.HAWAIIAN:
                return new Hawaiian();
            case Constants.PEPPERONI:
                return new Pepperoni();
            default:
                throw new IllegalArgumentException("Pizza type not found.");
        }
    }
}