package com.cs213.ordermanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cs213.ordermanager.util.Constants;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()

                    .commit();
        }
        final Button addDeluxe = findViewById(R.id.addDeluxe);
        addDeluxe.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPizzaActivity.class);
            intent.putExtra("type", Constants.DELUXE);
            startActivity(intent);
        });

        final Button addHawaiian = findViewById(R.id.addHawaiian);
        addHawaiian.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPizzaActivity.class);
            intent.putExtra("type", Constants.HAWAIIAN);
            startActivity(intent);
        });

        final Button addPepperoni = findViewById(R.id.addPepperoni);
        addPepperoni.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddPizzaActivity.class);
            intent.putExtra("type", Constants.PEPPERONI);
            startActivity(intent);
        });
    }
}

