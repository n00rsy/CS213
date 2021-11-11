package com.ordermanager.ordermanager.model.pizza;

import com.ordermanager.ordermanager.model.pizza.enums.Size;
import com.ordermanager.ordermanager.util.Constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Hawaiian extends Pizza {

    public Hawaiian() {
        this.size = Size.SMALL;
        this.toppings = new ArrayList(Arrays.asList(Constants.HAWAIIAN_TOPPINGS));
    }

    public double price() {
        int extraToppingsCount = toppings.size() - Constants.HAWAIIAN_TOPPINGS.length;
        if (extraToppingsCount < 0) extraToppingsCount = 0;

        return Constants.HAWAIIAN_BASE_PRICE + (extraToppingsCount * Constants.EXTRA_TOPPING_PRICE) + (size.ordinal() * Constants.SIZE_PRICE);
    }

}