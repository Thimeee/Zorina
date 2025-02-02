package com.zorina.lk.zorina;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Success message
        Button buttonSuccess = findViewById(R.id.successButton);
        buttonSuccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new SweetAlertDialog(TestActivity.this)
                        .setTitleText("Here's a message!")
                        .show();
            }
        });

        // 2. Confirmation message
        Button buttonWarning = findViewById(R.id.confirmationButton);
        buttonWarning.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new SweetAlertDialog(TestActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("You won't be able to recover this file!")
                        .setConfirmText("Delete!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });

        // 3. Error message
        Button buttonDanger = findViewById(R.id.errorButton);
        buttonDanger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new SweetAlertDialog(TestActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
            }
        });

        // 4. Loading message
        Button buttonLoading = findViewById(R.id.loadingButton);
        buttonLoading.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(TestActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading ...");
                pDialog.setCancelable(true);
                pDialog.show();
            }
        });

        // 5. Confirm success
        Button buttonConfirmSuccess = findViewById(R.id.confirmSuccessButton);
        buttonConfirmSuccess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new SweetAlertDialog(TestActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog
                                        .setTitleText("Deleted!")
                                        .setContentText("Your imaginary file has been deleted!")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            }
                        })
                        .show();
            }
        });
    }
}