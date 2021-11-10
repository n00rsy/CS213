package com.ordermanager.ordermanager.model;

import com.ordermanager.ordermanager.model.pizza.Pizza;

import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> pizzas;

    public Order() {
        pizzas = new ArrayList<>();
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }


}
