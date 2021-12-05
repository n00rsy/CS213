package com.cs213.ordermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cs213.ordermanager.model.Order;
import com.cs213.ordermanager.model.StoreOrders;

/**
 * Activity class for the store orders activity.
 *
 * @author Noor, Umar
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private StoreOrders storeOrders;
    private OrderManagerApp app;

    /**
     * Initializes global vars and sets the content view.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (OrderManagerApp) getApplicationContext();
        storeOrders = app.getStoreOrders();

        setContentView(R.layout.store_orders_activity);

        storeOrdersChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Updates the UI when an order is changed.
     */
    private void storeOrdersChanged() {

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        tableLayout.removeAllViews();

        TableRow title = new TableRow(this);
        TextView title1 = new TextView(this);
        TextView title2 = new TextView(this);
        TextView title3 = new TextView(this);

        title1.setText("Phone Number\t\t\t\t\t\t");
        title2.setText("Order Details\t\t\t\t\t\t");
        title3.setText("Total Price");

        title1.setTypeface(null, Typeface.BOLD);
        title2.setTypeface(null, Typeface.BOLD);
        title3.setTypeface(null, Typeface.BOLD);

        title.addView(title1);
        title.addView(title2);
        title.addView(title3);
        tableLayout.addView(title);

        for (Order order: storeOrders.getOrders()) {
            TableRow tableRow = new TableRow(this);

            tableRow.setOnClickListener(v -> {
                storeOrders.removeOrder(order);
                storeOrdersChanged();
            });

            TextView phoneNumber = new TextView(this);
            phoneNumber.setText(order.getPhoneNumber());

            TextView orderDetails = new TextView(this);
            orderDetails.setText(order.toString());

            TextView price = new TextView(this);
            price.setText(String.format("%,.2f", order.price() + order.tax()));

            tableRow.addView(phoneNumber);
            tableRow.addView(orderDetails);
            tableRow.addView(price);
            tableLayout.addView(tableRow);
        }
    }

    /**
     * Handles the back button click in the action bar.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}