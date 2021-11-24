package com.cs213.ordermanager;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

    }
}

