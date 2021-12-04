package com.cs213.ordermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cs213.ordermanager.model.Order;
import com.cs213.ordermanager.model.pizza.Pizza;

public class CurrentOrderActivity extends AppCompatActivity {

    Order currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        OrderManagerApp app = (OrderManagerApp) getApplicationContext();
        currentOrder = app.getCurrentOrder();
        //orderChanged();

        setContentView(R.layout.current_order_activity);
    }

    public void orderChanged() {

        for (Pizza pizza : currentOrder.getPizzas()) {
            TableRow tableRow = new TableRow(this);

            TextView details = new TextView(this);
            details.setText(pizza.toString());

            TextView price = new TextView(this);
            price.setText(String.format("%,.2f", pizza.price()));

            tableRow.addView(details);
            tableRow.addView(price);

            TableLayout tableLayout = findViewById(R.id.tableLayout);
            tableLayout.addView(tableRow);
        }

        TextView tax = findViewById(R.id.tax);
        System.out.println("tazx:  " + currentOrder.tax());
        tax.setText("Tax: " + String.format("%,.2f", currentOrder.tax()));

        TextView total = findViewById(R.id.total);
        total.setText("Total: " + String.format("%,.2f", currentOrder.price()));
    }

    public void submitOrder(View view) {

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