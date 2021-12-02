package com.cs213.ordermanager;

import android.app.Application;

import com.cs213.ordermanager.model.Order;
import com.cs213.ordermanager.model.StoreOrders;

public class OrderManagerApp extends Application {
    private StoreOrders storeOrders;
    private Order currentOrder;

    @Override
    public void onCreate() {
        super.onCreate();
        storeOrders = new StoreOrders();
        currentOrder = new Order();
    }

    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    public void setStoreOrders(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public boolean submitOrder (String phoneNumber) {
        if (currentOrder.isValid() && !storeOrders.contains(phoneNumber)) {
            storeOrders.addOrder(currentOrder);
            currentOrder = new Order();
            return true;
        }
        return false;
    }
}

