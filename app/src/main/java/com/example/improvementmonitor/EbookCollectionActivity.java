package com.example.improvementmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;


import com.example.improvementmonitor.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EbookCollectionActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private RecyclerView ebookTypeRecyclerView;
    private EbookTypeAdapter adp_EbookTypeAdapter;
    private List<EbookItem> lst_Ebook_itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_types);

        // Initialize UI components
        drawerLayout = findViewById(R.id.drawer_layout_other_pages);

        // Set up the toolbar
        setSupportActionBar(findViewById(R.id.toolbar_other_pages));

        // Set title and subtitle next to the hamburger icon
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ebook Collection");
        Objects.requireNonNull(getSupportActionBar()).setSubtitle("Explore various ebooks online");

        // Set up the drawer toggle
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        //Initialize it after you set the view of the content
        ebookTypeRecyclerView = findViewById(R.id.id_EbookTypeRecyclerView);

        //Set layout manager to display items in a vertical list
        ebookTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load ebook data into the RecyclerView
//        populateEbookData();

        // Initialize and set up data
        lst_Ebook_itemList = generateEbookItems();
        // Set up adapter with data -- see the usages section after this files main class announcing -line 15 approximately
        adp_EbookTypeAdapter = new EbookTypeAdapter(this, lst_Ebook_itemList);
        ebookTypeRecyclerView.setAdapter(adp_EbookTypeAdapter);

        // Set up Navigation Drawer listener (if you have a NavigationView)
        NavigationView navigationView = findViewById(R.id.nav_view_other_pages);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    handleNavigationItemSelected(item);
                    drawerLayout.closeDrawers();
                    return true;
                }
            });
        }
    }


    private List<EbookItem> generateEbookItems() {
        List<EbookItem> items = new ArrayList<>();

        // Example for Math category with subcategories
        List<EbookItem> mathSubItems = new ArrayList<>();

        mathSubItems.add(new EbookItem(R.drawable.ic_basic_math, "Basic Math", "Basic level Math"));
        mathSubItems.add(new EbookItem(R.drawable.ic_intermediate_math, "Intermediate Math", "Intermediate level Math"));
        mathSubItems.add(new EbookItem(R.drawable.ic_advanced_math, "Advanced Math", "Advanced level Math"));

        // Main Math category with sub-items
        EbookItem mathItem = new EbookItem(
                                            R.drawable.ic_calculator, "Math", "Mathematics category",
                                            R.drawable.ic_calculator, R.drawable.ic_calculator, R.drawable.ic_calculator);
        mathItem.setSubItems(mathSubItems);

        // Example for Physics category with subcategories
        List<EbookItem> physicsSubItems = new ArrayList<>();
        physicsSubItems.add(new EbookItem(R.drawable.ic_tuition_planner, "Classical Physics", "Introduction to Classical Physics"));
        physicsSubItems.add(new EbookItem(R.drawable.ic_recycle, "Quantum Physics", "Quantum Mechanics basics"));

        // Main Physics category with sub-items
        EbookItem physicsItem = new EbookItem(
                                            R.drawable.ic_repositories, "Physics", "Physics category",
                                            R.drawable.ic_repositories, R.drawable.ic_repositories, R.drawable.ic_repositories);
        physicsItem.setSubItems(physicsSubItems);

        // Add main items to the list
        items.add(mathItem);
        items.add(physicsItem);

        return items;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    private boolean handleNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_home_nav_menu) {
            startActivity(new Intent(EbookCollectionActivity.this, MainActivity.class));
        } else if (item.getItemId() == R.id.nav_notifications_nav_menu) {
            startActivity(new Intent(EbookCollectionActivity.this, NotificationActivity.class));
        } else if (item.getItemId() == R.id.nav_explore_nav_menu) {
            startActivity(new Intent(EbookCollectionActivity.this, ExploreActivity.class));
        } else if (item.getItemId() == R.id.nav_profile_nav_menu) {
            startActivity(new Intent(EbookCollectionActivity.this, ProfileActivity.class));
        } else if (item.getItemId() == R.id.nav_settings) {
            startActivity(new Intent(EbookCollectionActivity.this, SettingsActivity.class));
        } else if (item.getItemId() == R.id.nav_help) {
            startActivity(new Intent(EbookCollectionActivity.this, HelpActivity.class));
        } else if (item.getItemId() == R.id.nav_about) {
            startActivity(new Intent(EbookCollectionActivity.this, AboutActivity.class));
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
