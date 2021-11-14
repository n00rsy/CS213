package com.ordermanager.ordermanager.model;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class StoreOrders {
    private ArrayList<Order> orders;

    public StoreOrders() {
        orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public boolean contains(String phoneNumber) {
        for (Order order : orders) {
            if (order.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

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
