package com.ordermanager.ordermanager.model.pizza;

import com.ordermanager.ordermanager.model.pizza.enums.Size;
import com.ordermanager.ordermanager.model.pizza.enums.Topping;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class for Pizza.
 * Deluxe, Pepperoni, and Hawaiian extend this class.
 *
 * @author Noor, Umar
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public abstract double price();

    protected List<Topping> getRemovedToppings(Topping[] defaultToppings) {
        ArrayList<Topping> removedToppings = new ArrayList<>();
        for (Topping defaultTopping : defaultToppings) {
            boolean found = false;
            for (Topping userTopping : toppings) {
                if (defaultTopping == userTopping) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                removedToppings.add(defaultTopping);
            }
        }
        return removedToppings;
    }

    protected List<Topping> getAddedToppings(Topping[] defaultToppings) {
        ArrayList<Topping> addedToppings = new ArrayList<>();
        for (Topping userTopping : toppings) {
            boolean found = false;
            for (Topping defaultTopping : defaultToppings) {
                if (defaultTopping == userTopping) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                addedToppings.add(userTopping);
            }
        }
        return addedToppings;
    }
}
