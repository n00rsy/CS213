package com.cs213.ordermanager.model.pizza;

import com.cs213.ordermanager.model.pizza.enums.Size;
import com.cs213.ordermanager.model.pizza.enums.Topping;
import com.cs213.ordermanager.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class describing a pepperoni pizza.
 *
 * @author Noor, Umar
 */
public class Pepperoni extends Pizza {

    /**
     * Constructor for the class.
     * Initializes size and toppings.
     */
    public Pepperoni() {
        this.size = Size.SMALL;
        this.toppings = new ArrayList(Arrays.asList(Constants.PEPPERONI_TOPPINGS));
    }

    /**
     * Calculates the price of this pizza given its toppings and size, without tax
     *
     * @return The price of pizza
     */
    public double price() {
        int extraToppingsCount = toppings.size() - Constants.PEPPERONI_TOPPINGS.length;
        if (extraToppingsCount < 0) extraToppingsCount = 0;

        return Constants.PEPPERONI_BASE_PRICE + (extraToppingsCount * Constants.EXTRA_TOPPING_PRICE) + (size.ordinal() * Constants.SIZE_PRICE);
    }

    /**
     * Generates a string representation of this pizza object.
     *
     * @return string description of this pizza.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size.toString() + " Pepperoni Pizza\n");

        for (Topping topping : getAddedToppings(Constants.PEPPERONI_TOPPINGS)) {
            sb.append("\t+ " + topping + "\n");
        }

        for (Topping topping : getRemovedToppings(Constants.PEPPERONI_TOPPINGS)) {
            sb.append("\t- " + topping + "\n");
        }
        return sb.toString();
    }
}
