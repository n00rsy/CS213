package com.ordermanager.ordermanager.model;

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

    public void export() {

    }
}
