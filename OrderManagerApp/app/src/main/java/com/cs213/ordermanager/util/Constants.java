package com.cs213.ordermanager.util;

import com.cs213.ordermanager.model.pizza.enums.Topping;

/**
 * A class containing constants for use throughout the solution.
 */
public class Constants {
    public static final String DELUXE = "deluxe";
    public static final String HAWAIIAN = "hawaiian";
    public static final String PEPPERONI = "pepperoni";

    public static final Topping[] DELUXE_TOPPINGS = {Topping.PEPPERONI, Topping.SAUSAGE, Topping.BELL_PEPPERS, Topping.MUSHROOM, Topping.ONION};
    public static final Topping[] HAWAIIAN_TOPPINGS = {Topping.HAM, Topping.PINEAPPLE};
    public static final Topping[] PEPPERONI_TOPPINGS = {Topping.PEPPERONI};

    public static final double DELUXE_BASE_PRICE = 12.99;
    public static final double HAWAIIAN_BASE_PRICE = 10.99;
    public static final double PEPPERONI_BASE_PRICE = 8.99;

    public static final double EXTRA_TOPPING_PRICE = 1.49;
    public static final double SIZE_PRICE = 2.00;
    public static final double TAX_RATE = 0.06625;
    public static final int MAX_TOPPINGS = 7;
}
