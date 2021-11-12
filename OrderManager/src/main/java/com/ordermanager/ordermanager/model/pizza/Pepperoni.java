package com.ordermanager.ordermanager.model.pizza;

import com.ordermanager.ordermanager.model.pizza.enums.Size;
import com.ordermanager.ordermanager.model.pizza.enums.Topping;
import com.ordermanager.ordermanager.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Pepperoni extends Pizza {

    public Pepperoni() {
        this.size = Size.SMALL;
        this.toppings = new ArrayList(Arrays.asList(Constants.PEPPERONI_TOPPINGS));
    }

    public double price() {
        int extraToppingsCount = toppings.size() - Constants.PEPPERONI_TOPPINGS.length;
        if (extraToppingsCount < 0) extraToppingsCount = 0;

        return Constants.PEPPERONI_BASE_PRICE + (extraToppingsCount * Constants.EXTRA_TOPPING_PRICE) + (size.ordinal() * Constants.SIZE_PRICE);
    }


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
