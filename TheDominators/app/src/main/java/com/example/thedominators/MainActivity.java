package com.example.thedominators;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new HomFragment()).commit();
                        break;
                    case R.id.dashboard:

                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new ProfileFragment()).commit();

                        break;
                    case R.id.explore:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new ExploreFragment()).commit();
                        break;
                }
                menuItem.setChecked(true);
                return false;
            }
        });
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,new HomFragment()).commit();

        }

    }

}