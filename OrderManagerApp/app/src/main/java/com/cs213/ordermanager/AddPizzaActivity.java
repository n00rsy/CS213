package com.cs213.ordermanager;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.cs213.ordermanager.model.Order;
import com.cs213.ordermanager.model.pizza.Pizza;
import com.cs213.ordermanager.model.pizza.PizzaMaker;
import com.cs213.ordermanager.model.pizza.enums.Size;
import com.cs213.ordermanager.model.pizza.enums.Topping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class AddPizzaActivity extends AppCompatActivity {

    private Pizza currentPizza;
    private ArrayList<CheckBox> checkboxes = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String pizzaType = intent.getStringExtra("type");

        currentPizza = PizzaMaker.createPizza(pizzaType);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.add_pizza_activity, null);

        LinearLayout presetToppingsLayout = v.findViewById(R.id.presetToppingsLayout);
        LinearLayout additionalToppingsLayout = v.findViewById(R.id.additionalToppingsLayout);

        createToppingCheckBoxes(presetToppingsLayout, currentPizza.getToppings(), true);
        createToppingCheckBoxes(additionalToppingsLayout, Arrays.stream(Topping.values()).filter(t -> !currentPizza.getToppings().contains(t)).collect(Collectors.toList()), false);

        setContentView(v);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.pizzaTypeTextView);
        textView.setText("Order new " + pizzaType + " Pizza");

    }

    private ArrayList<Topping> getSelectedToppings() {
        ArrayList<Topping> selectedToppings = new ArrayList<>();
        for (CheckBox checkBox : checkboxes) {
            if (checkBox.isChecked()) {
                selectedToppings.add(Topping.valueOf(checkBox.getText().toString()));
            }
        }
        return selectedToppings;
    }

    private void createToppingCheckBoxes(LinearLayout layout, List<Topping> toppings, boolean checked) {

        for (Topping topping : toppings) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(topping.toString());
            checkBox.setChecked(checked);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked && getSelectedToppings().size() > 7) {
                    buttonView.setChecked(false);

                    Context context = getApplicationContext();
                    CharSequence text = "Can't have more than 7 toppings!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });
            checkboxes.add(checkBox);
            layout.addView(checkBox);
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

    public void orderPizza(View view) {

        currentPizza.setToppings(getSelectedToppings());

        RadioGroup radioGroup = findViewById(R.id.sizeRadioGroup);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        currentPizza.setSize(Size.valueOf(radioButton.getText().toString().toUpperCase()));

        System.out.println(currentPizza.getSize());

        OrderManagerApp app = (OrderManagerApp) getApplicationContext();
        Order currentOrder = app.getCurrentOrder();

        currentOrder.addPizza(currentPizza);

        NavUtils.navigateUpFromSameTask(this);

        Context context = getApplicationContext();
        CharSequence text = "Pizza successfully ordered.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
