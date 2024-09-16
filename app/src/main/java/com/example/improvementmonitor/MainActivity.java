package com.example.improvementmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.grevocabularyapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.toolbar));

        // Set up the drawer layout and toggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Set up navigation view listener
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleNavigationItemSelected(item); // Call method to handle selection
                drawerLayout.closeDrawers(); // Close the drawer after selection
                return true;
            }
        });

        // Set up bottom navigation view listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return handleBottomNavigationItemSelected(item); // Call method to handle selection
            }
        });

        // Set up button listeners
        /*
        findViewById(R.id.btn_add_word).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_start_quiz).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_daily_challenge).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DailyChallengeActivity.class);
            startActivity(intent);
        });
         */
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean handleNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_home) {
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        } else if (item.getItemId() == R.id.nav_notifications) {
            bottomNavigationView.setSelectedItemId(R.id.action_notifications);
        } else if (item.getItemId() == R.id.nav_explore) {
            bottomNavigationView.setSelectedItemId(R.id.action_explore);
        } else if (item.getItemId() == R.id.nav_profile) {
            bottomNavigationView.setSelectedItemId(R.id.action_profile);
        } else if (item.getItemId() == R.id.nav_add_word) {
            startActivity(new Intent(MainActivity.this, AddWordActivity.class));
        } else if (item.getItemId() == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else if (item.getItemId() == R.id.nav_help) {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        } else if (item.getItemId() == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        return true;
    }

    private boolean handleBottomNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            // Handle home action
        } else if (item.getItemId() == R.id.action_notifications) {
            // Handle notifications action
        } else if (item.getItemId() == R.id.action_explore) {
            // Handle explore action
        } else if (item.getItemId() == R.id.action_profile) {
            // Handle profile action
        }
        return true;
    }
}
