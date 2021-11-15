package com.ordermanager.ordermanager.model;

import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.util.Configuration;
import com.ordermanager.ordermanager.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A model class describing an Order of pizzas.
 *
 * @author Noor, Umar
 */
public class Order {
    private ArrayList<Pizza> pizzas;
    private String phoneNumber;

    /**
     * Constructor for this class.
     */
    public Order() {
        pizzas = new ArrayList<>();
    }

    /**
     * Phone number accessor
     *
     * @return This order's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Phone number mutator
     *
     * @param phoneNumber New phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * pizza List accessor
     *
     * @return This Order's pizzas list
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds the pizza to this order
     *
     * @param pizza The pizza to add
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a pizza from this order
     *
     * @param pizza The pizza to remove
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Calculates the price of the entire order
     *
     * @return Total price for the order
     */
    public double price() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }

    /**
     * Calculates the tax on this order
     *
     * @return Total tax for this order
     */
    public double tax() {
        return price() * Constants.TAX_RATE;
    }

    /**
     * Checks if this order has a valid phone number and greater than one pizza
     *
     * @return True if valid, false otherwise
     */
    public boolean isValid() {
        return pizzas.size() > 0 && phoneNumber != null && phoneNumber.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$");
    }

    /**
     * Returns the pizzas in this order as a string
     *
     * @return String representation of the pizza list
     */
    public String getPizzasAsString() {
        StringBuilder sb = new StringBuilder();
        for (Pizza pizza : pizzas) {
            sb.append(pizza.toString());
        }
        return sb.toString();
    }

    /**
     * Returns the string representation of this Order object
     *
     * @return
     */
    @Override
    public String toString() {
        return "Phone number: " + String.format(Configuration.PRICE_FORMAT, price() + tax()) +
                "\nPizzas: \n" + getPizzasAsString();

    }
}
