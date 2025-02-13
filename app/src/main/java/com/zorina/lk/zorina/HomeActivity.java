package com.zorina.lk.zorina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView homeSearchIcon = findViewById(R.id.homeSearchIcon);
        homeSearchIcon.setOnClickListener(view -> {
            Intent goSearchIntent = new Intent(HomeActivity.this, CustomerSearchActivity.class);
            startActivity(goSearchIntent);
        });

        TextView CoustomerAccountIcon = findViewById(R.id.CoustomerAccountIcon);
        CoustomerAccountIcon.setOnClickListener(view -> {
            Intent goAccountIntent = new Intent(HomeActivity.this, UserAccountActivity.class);
            startActivity(goAccountIntent);
        });

        TextView CoustomerOrdersIcon = findViewById(R.id.CoustomerOrdersIcon);
        CoustomerOrdersIcon.setOnClickListener(view -> {
            Intent goOrdersIntent = new Intent(HomeActivity.this, CoustomerOrdersActivity.class);
            startActivity(goOrdersIntent);
        });

        TextView Homecarticon = findViewById(R.id.Homecarticon);
        Homecarticon.setOnClickListener(view -> {
            Intent goCartIntent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(goCartIntent);
        });

        FloatingActionButton Homesingaleicon = findViewById(R.id.homesingaleicon);
        Homesingaleicon.setOnClickListener(view -> {
            Intent goSingaleIntent = new Intent(HomeActivity.this, SingleProductViewActivity.class);
            startActivity(goSingaleIntent);
        });



    }
}