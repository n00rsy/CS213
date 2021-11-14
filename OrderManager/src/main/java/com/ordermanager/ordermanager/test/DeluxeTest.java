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

public class DeluxeTest {

    private static final double DELTA = 1e-15;


    @Test
    public void defaultToppingsSmallPizzaTest() {
        Pizza pizza = new Deluxe();
        pizza.setSize(Size.SMALL);
        Assert.assertEquals(pizza.price(), 12.99, DELTA);
    }

    @Test
    public void defaultToppingsMediumPizzaTest() {
        Pizza pizza = new Deluxe();
        pizza.setSize(Size.SMALL);
        Assert.assertEquals(pizza.price(), 14.99, DELTA);
    }

    @Test
    public void defaultToppingsLargePizzaTest() {
        Pizza pizza = new Deluxe();
        pizza.setSize(Size.LARGE);
        Assert.assertEquals(pizza.price(), 16.99, DELTA);
    }

    @Test
    public void removeToppingsTest() {
        Pizza pizza = new Deluxe();
        pizza.setToppings(new ArrayList<>());
        Assert.assertEquals(pizza.price(), 12.99, DELTA);
    }

    @Test
    public void addToppingTest() {
        Pizza pizza = new Deluxe();
        ArrayList<Topping> toppings = new ArrayList(Arrays.asList(Constants.DELUXE_TOPPINGS));
        toppings.add(Topping.PINEAPPLE);
        pizza.setToppings(toppings);
        Assert.assertEquals(pizza.price(), 14.48, DELTA);
    }

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
