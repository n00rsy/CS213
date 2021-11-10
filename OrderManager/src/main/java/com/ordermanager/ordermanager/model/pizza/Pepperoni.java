package com.ordermanager.ordermanager.model.pizza;

import java.util.ArrayList;

public class Pepperoni extends Pizza {

    public double price() {
        return 0;
    }

    @Override
    public ArrayList<Topping> getToppings() {
        return null;
    }
}
