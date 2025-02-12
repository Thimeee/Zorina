package com.zorina.lk.zorina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView customerSignupText = findViewById(R.id.customerSignUpChangeText);
        customerSignupText.setOnClickListener(view -> {
            Intent goSignUpIntent = new Intent(MainActivity.this, CustomerSignUpActivity.class);
            startActivity(goSignUpIntent);
        });

        TextView customerSignInButton = findViewById(R.id.customerSignInButton);
        customerSignInButton.setOnClickListener(view -> {
            Intent goHomeIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(goHomeIntent);
        });

    }
}