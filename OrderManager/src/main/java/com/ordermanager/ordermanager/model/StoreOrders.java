package com.ordermanager.ordermanager.model;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * A model class containing a master list of orders in the system
 *
 * @author Noor, Umar
 */
public class StoreOrders {
    private ArrayList<Order> orders;

    /**
     * Constructor for this class
     */
    public StoreOrders() {
        orders = new ArrayList<>();
    }

    /**
     * Orders list accessor
     * @return  This StoreOrders's orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds an order to the list
     * @param order The order to add
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Removes an order from the list
     * @param order The order to remove
     */
    public void removeOrder(Order order) {
        orders.remove(order);
    }

    /**
     * Checks if the system contains an order with a phone number
     * @param phoneNumber   The phone number to check against
     * @return  True if found a match, false otherwise
     */
    public boolean contains(String phoneNumber) {
        for (Order order : orders) {
            if (order.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Exports the contents of the orders list to a txt file.
     *
     * @param path  Path to the output file
     */
    public void export(String path) {
        try {
            File myObj = new File(path);
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(myObj);
            myWriter.write(this.toString());
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred while exporting the file.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders) {
            sb.append(order.toString());
        }
        return sb + "\n";
    }
}
