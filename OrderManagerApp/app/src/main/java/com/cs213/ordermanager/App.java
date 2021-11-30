package com.cs213.ordermanager;

import android.app.Application;

import com.cs213.ordermanager.model.StoreOrders;

public class App extends Application {
    private StoreOrders storeOrders;

    @Override
    public void onCreate() {
        super.onCreate();
        storeOrders = new StoreOrders();
    }

    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    public void setStoreOrders(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
    }
}
