package com.ordermanager.ordermanager.model.pizza;

import com.ordermanager.ordermanager.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Deluxe extends Pizza {

    public Deluxe() {
        this.size = Size.SMALL;
        this.toppings = new ArrayList(Arrays.asList(Constants.DELUXE_TOPPINGS));
    }

    public double price() {
        int extraToppingsCount = toppings.size() - Constants.DELUXE_TOPPINGS.length;
        if (extraToppingsCount < 0) extraToppingsCount = 0;

        return Constants.DELUXE_BASE_PRICE + (extraToppingsCount * Constants.EXTRA_TOPPING_PRICE) + (size.ordinal() * Constants.SIZE_PRICE);
    }

}
