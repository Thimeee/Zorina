package com.zorina.lk.zorina;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.zorina.lk.zorina.model.AdminSessionManager;

public class SpalshScreenAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spalsh_screen_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView imageView = findViewById(R.id.SplashimageViewAdmin);
        SpringAnimation springAnimation = new SpringAnimation(imageView, DynamicAnimation.TRANSLATION_Y);

        SpringForce springForce = new SpringForce();
        springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springForce.setFinalPosition(1000f);

        springAnimation.setSpring(springForce);
        springAnimation.start();

      AdminSessionManager  loginAdminCheck= new AdminSessionManager(this);

        new Handler().postDelayed(()->{
            if(loginAdminCheck.isLoggedIn()){
                Intent intent = new Intent(SpalshScreenAdminActivity.this,AdminDashboardActivity.class);
                startActivity(intent);
                finish();
            }else {
                Intent intent = new Intent(SpalshScreenAdminActivity.this,AdminSignInActivity.class);
                startActivity(intent);
                finish();
            }


        },2000);
    }
}