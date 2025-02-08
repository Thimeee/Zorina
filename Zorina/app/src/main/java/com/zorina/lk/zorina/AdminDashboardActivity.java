package com.zorina.lk.zorina;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.zorina.lk.zorina.navigation.AddProductFragment;
import com.zorina.lk.zorina.navigation.AllProductFragment;
import com.zorina.lk.zorina.navigation.CoustomerFragment;
import com.zorina.lk.zorina.navigation.DashboardFragment;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadFragment(new DashboardFragment());

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar1 =findViewById(R.id.tool_bar1);
        NavigationView navigationView1 = findViewById(R.id.navigation_view_1);

        setSupportActionBar(toolbar1);


        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(navigationView1);

            }
        });

        navigationView1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.nav_dashboard){
                    loadFragment(new DashboardFragment());

                }else if(item.getItemId() == R.id.nav_add_product){
                    loadFragment(new AddProductFragment());

                }else if(item.getItemId() == R.id.upadate_product){
                    loadFragment(new AllProductFragment());

                }else if(item.getItemId() == R.id.nav_order){
                    loadFragment(new AdminOdresFragment());

                }else if(item.getItemId() == R.id.nav_admin){
                    loadFragment(new AdminAccountFragment());

                }else if(item.getItemId() == R.id.nav_cus){
                    loadFragment(new CoustomerFragment());

                }



                toolbar1.setTitle(item.getTitle());
                drawerLayout.closeDrawers();
                return true;

            }
        });

    }
    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView , fragment ,null)
                .setReorderingAllowed(true)
                .commit();
    }
}