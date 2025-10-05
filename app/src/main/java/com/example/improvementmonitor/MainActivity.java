package com.example.improvementmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import com.ebook.BookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.im.calculator.CalculatorActivity;
import com.im.calculator.ExtendedCalculatorActivity;

//Transitions and Fade in out Animation Imports
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


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
        // Load animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        final ScrollView scrollView = findViewById(R.id.home_scroll_view);

        ///////////////////////// CardViews all objects in main layout /////////////////////////////
        // Find the Task Management CardView
        CardView cardTaskManagement = findViewById(R.id.card_task_management);
        // Find the Ebook Collection Activity CardView
        CardView ebookCollection_activity = findViewById(R.id.card_ebook_collection);
        // Find the Book Activity CardView
        CardView book_activity_pck_ebook = findViewById(R.id.card_book_activity);
        // Find the Book Activity CardView
        CardView calculator_pck_calculator = findViewById(R.id.card_calculator);
        // Find the Book Activity CardView
        CardView calculator_pck_calculator_extended = findViewById(R.id.card_calculator_extended);
        // Find the Book Activity CardView
        CardView walletOption = findViewById(R.id.card_wallets);

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
                int itemId = item.getItemId();

                if (itemId == R.id.home_scroll_view_action) {
                    // Show the ScrollView only when Home is selected
                    scrollView.setVisibility(View.VISIBLE);
                } else {
                    // Hide the ScrollView for other options
                    scrollView.setVisibility(View.GONE);
                }
//              For fading out the current navbar in this same activity
//                bottomNavigationView.startAnimation(fadeOut);
//                bottomNavigationView.setVisibility(View.GONE);
//                For fading in the current navbar but different option in this same activity
//                bottomNavigationView.startAnimation(fadeIn);
//                bottomNavigationView.setVisibility(View.VISIBLE);

                return true; // Return true to indicate that the item was selected
            }
        });

        // Set an onClickListener on the Task Management CardView
        cardTaskManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToTaskManagement();
            }
        });

        // Set an onClickListener on the EbookCollectionActivity CardView
        ebookCollection_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToEbookCollectionActivity();
            }
        });

        // Set an onClickListener on the EbookCollectionActivity CardView
        book_activity_pck_ebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToBookActivity();
            }
        });

        // Set an onClickListener on the CalculatorActivity CardView
        calculator_pck_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToCalculatorActivity();
            }
        });

        // Set an onClickListener on the CalculatorActivity CardView
        calculator_pck_calculator_extended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToExtendedCalculatorActivity();
            }
        });

        walletOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { navigateToWalletActivity(); }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean handleNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_home_nav_menu) {
            bottomNavigationView.setSelectedItemId(R.id.home_scroll_view_action);
        } else if (item.getItemId() == R.id.nav_notifications_nav_menu) {
            bottomNavigationView.setSelectedItemId(R.id.action_notifications);
        } else if (item.getItemId() == R.id.nav_explore_nav_menu) {
            bottomNavigationView.setSelectedItemId(R.id.action_explore);
        } else if (item.getItemId() == R.id.nav_profile_nav_menu) {
            bottomNavigationView.setSelectedItemId(R.id.action_profile);
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
        if (item.getItemId() == R.id.home_scroll_view_action) {
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

    private void navigateToTaskManagement() {

        Intent intent = new Intent(MainActivity.this, TaskManagementActivity.class);
        startActivity(intent);

    }

    private void navigateToEbookCollectionActivity() {

        Intent intent = new Intent(MainActivity.this, EbookCollectionActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

    }

    private void navigateToBookActivity() {

        Intent intent = new Intent(MainActivity.this, BookActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

    }

    private void navigateToCalculatorActivity() {
        Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    private void navigateToExtendedCalculatorActivity() {
        Intent intent = new Intent(MainActivity.this, ExtendedCalculatorActivity.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    private void navigateToWalletActivity() {
        Intent intent = new Intent(MainActivity.this, com.wallet.ui.WalletListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get BottomNavigationView and make it visible with a fade-in animation
        View bottomNavMainAc = findViewById(R.id.bottom_navigation);
        bottomNavMainAc.setVisibility(View.VISIBLE); // Ensure visibility is set to VISIBLE

        // Apply fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        bottomNavMainAc.startAnimation(fadeIn);
    }
}
