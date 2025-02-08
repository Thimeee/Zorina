package com.zorina.lk.zorina;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.zorina.lk.zorina.model.Admin;
import com.zorina.lk.zorina.model.AdminAuth;
import com.zorina.lk.zorina.model.AdminSessionManager;
import com.zorina.lk.zorina.model.CustomAlert;

public class AdminSignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView email=findViewById(R.id.adminSignInEmail);
        TextView password=findViewById(R.id.adminSignInPassword);


        Admin admin=new Admin();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        TextView AdminSigninButton = findViewById(R.id.adminSignInButton);
        AdminSigninButton.setOnClickListener(view -> {


            admin.setEmail(email.getText().toString());
            admin.setPassword(password.getText().toString());

            if(admin.getEmail().isEmpty()){
                CustomAlert.showErrorAlert(AdminSignInActivity.this,"Please Enter Email");
                email.setError("Please Enter Email");
                email.requestFocus();
                return;

            }

//            else if(admin.getEmail().matches(emailPattern)){
//                CustomAlert.showErrorAlert(AdminSignInActivity.this,"Please Invalid Email");
//                email.setError("Please Invalid Email");
//                email.requestFocus();
//                return;
//
//            }
//
            else if (admin.getPassword().isEmpty()) {
                CustomAlert.showErrorAlert(AdminSignInActivity.this,"Please Enter Password");
                password.setError("Please Enter User Name");
                password.requestFocus();
                return;
            }else {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Admin").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        boolean isAuthenticated = false;

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String storedEmail = document.getString("Email");
                            String storedPassword = document.getString("Password");

                            if (storedEmail != null && storedPassword != null &&
                                    storedEmail.equals(admin.getEmail()) &&
                                    storedPassword.equals(admin.getPassword())) {

                                isAuthenticated = true;
                                String adminMobile = document.getString("Mobile");
                                String adminName = document.getString("Name");


                              AdminSessionManager adminSessionManger = new AdminSessionManager(this);
                              AdminAuth adminsession = new AdminAuth(adminName,adminMobile,storedEmail);

                                adminSessionManger.saveAdmin(adminsession);

                                Intent goAdminDashboardIntent = new Intent(AdminSignInActivity.this, AdminDashboardActivity.class);
                                startActivity(goAdminDashboardIntent);
                                finish();
                                break;
                            }
                        }

                        if (!isAuthenticated) {
                            CustomAlert.showErrorAlert(AdminSignInActivity.this, "Invalid Email or Password");
                        }
                    } else {
                        CustomAlert.showErrorAlert(AdminSignInActivity.this, "Error fetching data. Please try again.");
                    }
                });
            }



        });




    }
}