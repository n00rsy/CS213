package com.ordermanager.ordermanager.model;

import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.util.Constants;

import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> pizzas;
    private String phoneNumber;

    public Order() {
        pizzas = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    public double price() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }

    public double tax() {
        return price() * Constants.TAX_RATE;
    }

    public boolean isValid() {
        return pizzas.size() > 0 && phoneNumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$");
    }
}
