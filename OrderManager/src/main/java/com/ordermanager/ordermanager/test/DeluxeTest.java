package com.ordermanager.ordermanager.test;

import com.ordermanager.ordermanager.model.pizza.Deluxe;
import com.ordermanager.ordermanager.model.pizza.Pizza;
import com.ordermanager.ordermanager.model.pizza.enums.Size;
import com.ordermanager.ordermanager.model.pizza.enums.Topping;
import com.ordermanager.ordermanager.util.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Junit test class for Deluxe.java
 *
 * @author Noor, Umar
 */
public class DeluxeTest {

    private static final double DELTA = 1e-5;

    /**
     * Ensures that the default pizza configuration returns the correct price.
     */
    @Test
    public void defaultToppingsSmallPizzaTest() {
        Pizza pizza = new Deluxe();
        pizza.setSize(Size.SMALL);
        Assert.assertEquals(pizza.price(), 12.99, DELTA);
    }

    /**
     * Ensures that the pizza with medium size returns the correct price.
     */
    @Test
    public void defaultToppingsMediumPizzaTest() {
        Pizza pizza = new Deluxe();
        pizza.setSize(Size.MEDIUM);
        Assert.assertEquals(pizza.price(), 14.99, DELTA);
    }


    /**
     * Checks if the pizza with large size and default toppings returns the correct price.
     */
    @Test
    public void defaultToppingsLargePizzaTest() {
        Pizza pizza = new Deluxe();
        pizza.setSize(Size.LARGE);
        Assert.assertEquals(pizza.price(), 16.99, DELTA);
    }

    /**
     * Ensures that removing toppings returns the same price as an unedited pizza.
     */
    @Test
    public void removeToppingsTest() {
        Pizza pizza = new Deluxe();
        pizza.setToppings(new ArrayList<>());
        Assert.assertEquals(pizza.price(), 12.99, DELTA);
    }

    /**
     * Ensures that adding toppings to a pizza results in the correct increase of its price.
     */
    @Test
    public void addToppingTest() {
        Pizza pizza = new Deluxe();
        ArrayList<Topping> toppings = new ArrayList(Arrays.asList(Constants.DELUXE_TOPPINGS));
        toppings.add(Topping.PINEAPPLE);
        pizza.setToppings(toppings);
        Assert.assertEquals(pizza.price(), 14.48, DELTA);
    }

    /**
     * Ensures that adding toppings and changing the pizza size results in the correct price.
     */
    @Test
    public void addToppingLargeTest() {
        Pizza pizza = new Deluxe();
        ArrayList<Topping> toppings = new ArrayList(Arrays.asList(Constants.DELUXE_TOPPINGS));
        toppings.add(Topping.PINEAPPLE);
        pizza.setSize(Size.LARGE);
        pizza.setToppings(toppings);
        Assert.assertEquals(pizza.price(), 18.48, DELTA);
    }

}
