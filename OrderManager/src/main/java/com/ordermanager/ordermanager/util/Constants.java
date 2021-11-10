package com.ordermanager.ordermanager.util;

import com.ordermanager.ordermanager.model.pizza.Topping;

public class Constants {
    public static final String DELUXE = "deluxe";
    public static final String HAWAIIAN = "hawaiian";
    public static final String PEPPERONI = "pepperoni";

    public static final Topping[] DELUXE_TOPPINGS = {Topping.PEPPERONI, Topping.SAUSAGE, Topping.BELL_PEPPERS, Topping.MUSHROOM, Topping.ONION};

    public static final double EXTRA_TOPPING_PRICE = 1.49;
    public static final double SIZE_PRICE = 2.00;
    public static final double DELUXE_BASE_PRICE = 12.99;
    public static final double TAX_RATE = 1.06625;
}
