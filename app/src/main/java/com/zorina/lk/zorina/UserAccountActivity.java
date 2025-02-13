package com.zorina.lk.zorina;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UserAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Button showBottomSheetButton = findViewById(R.id.ChangePasswordButton);

        showBottomSheetButton.setOnClickListener(v -> {

            // Inflate the bottom sheet layout
            View bottomSheetView = getLayoutInflater().inflate(R.layout.activity_user_profile_update, null);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(UserAccountActivity.this);
            bottomSheetDialog.setContentView(bottomSheetView);

            // Find views inside the bottom sheet
            Button actionOne = bottomSheetView.findViewById(R.id.UserUpdatePasswordBtn);
            EditText text01 = bottomSheetView.findViewById(R.id.UserOldPassword);
            EditText text02 = bottomSheetView.findViewById(R.id.UserNewPassword);
            EditText text03 = bottomSheetView.findViewById(R.id.UserReTypePassword);

            actionOne.setOnClickListener(view -> {
                String oldpassword = text01.getText().toString().trim();
                String newPassword = text02.getText().toString().trim();
                String reNewPassword = text03.getText().toString().trim();

                if (oldpassword.isEmpty()) {
                    new SweetAlertDialog(UserAccountActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Please Fill Old Password!")
                            .show();
                } else if (newPassword.isEmpty()) {
                    new SweetAlertDialog(UserAccountActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Please Fill New Password!")
                            .show();
                } else if (reNewPassword.isEmpty()) {
                    new SweetAlertDialog(UserAccountActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Please Fill Re-Type Password!")
                            .show();
                } else {

                    new SweetAlertDialog(UserAccountActivity.this)
                            .setTitleText("Password Updated Successfully!")
                            .show();
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.show();
        });
    }
}