package com.zorina.lk.zorina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CustomerSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_sign_up);

        // Find the root view by its correct ID
        View rootView = findViewById(R.id.coustomer_signup); // Ensure this ID exists in XML
        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            TextView customerSignupText = findViewById(R.id.customerSignInChangeText);
            customerSignupText.setOnClickListener(view -> {
                Intent goSignUpIntent = new Intent(CustomerSignUpActivity.this, MainActivity.class);
                startActivity(goSignUpIntent);
            });

        } else {
            System.out.println("Error: View with ID 'coustomer_signup' not found!");
        }
    }
}
