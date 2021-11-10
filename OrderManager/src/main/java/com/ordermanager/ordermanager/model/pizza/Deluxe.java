package com.ordermanager.ordermanager.model.pizza;

import java.util.ArrayList;
import java.util.Arrays;

public class Deluxe extends Pizza {

    public Deluxe() {
        this.size = Size.MEDIUM;
        this.toppings = new ArrayList(Arrays.asList(Topping.PEPPERONI, Topping.SAUSAGE, Topping.BELL_PEPPERS, Topping.MUSHROOM, Topping.ONION));
    }

    public double price() {
        return 0;
    }

    @Override
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }
}
