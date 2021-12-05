package com.cs213.ordermanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.cs213.ordermanager.model.Order;
import com.cs213.ordermanager.model.StoreOrders;
import com.cs213.ordermanager.model.pizza.Pizza;

public class CurrentOrderActivity extends AppCompatActivity {

    Order currentOrder;
    OrderManagerApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        app = (OrderManagerApp) getApplicationContext();
        currentOrder = app.getCurrentOrder();
        setContentView(R.layout.current_order_activity);
        orderChanged();
    }

    public void orderChanged() {

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        tableLayout.removeAllViews();

        TableRow title = new TableRow(this);
        TextView title1 = new TextView(this);
        TextView title2 = new TextView(this);

        title1.setText("Pizza Details\t\t\t\t\t\t");
        title2.setText("Price");

        title.addView(title1);
        title.addView(title2);
        tableLayout.addView(title);

        for (Pizza pizza : currentOrder.getPizzas()) {
            TableRow tableRow = new TableRow(this);

            tableRow.setOnClickListener(v -> {
                currentOrder.removePizza(pizza);
                orderChanged();
            });

            TextView details = new TextView(this);
            details.setText(pizza.toString());

            TextView price = new TextView(this);
            price.setText(String.format("%,.2f", pizza.price()));

            tableRow.addView(details);
            tableRow.addView(price);

            tableLayout.addView(tableRow);
        }

        TextView tax = findViewById(R.id.tax);
        tax.setText("Tax: " + String.format("%,.2f", currentOrder.tax()));

        TextView total = findViewById(R.id.total);
        total.setText("Total: " + String.format("%,.2f", currentOrder.price()));
    }

    public void submitOrder(View view) {

        TextView phoneNumber = findViewById(R.id.editTextPhone);
        currentOrder.setPhoneNumber(phoneNumber.getText().toString());

        StoreOrders storeOrders = app.getStoreOrders();

        if (currentOrder.isValid() && !storeOrders.contains(phoneNumber.getText().toString())) {
            app.submitOrder();
            System.out.println(storeOrders.toString());
            NavUtils.navigateUpFromSameTask(this);
            Toast.makeText(getApplicationContext(), "Order added.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please submit an order with one or more pizzas and a valid phone number.", Toast.LENGTH_SHORT).show();
        }
    }

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