package com.ordermanager.ordermanager.model;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    public abstract double price();
}
