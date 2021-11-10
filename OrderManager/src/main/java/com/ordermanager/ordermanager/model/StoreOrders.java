package com.ordermanager.ordermanager.model;

import java.util.ArrayList;

public class StoreOrders {
    private ArrayList<Order> orders;

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public StoreOrders() {
        orders = new ArrayList<>();
    }

    public void export() {

    }
}
