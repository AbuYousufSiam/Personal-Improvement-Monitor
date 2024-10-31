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


import com.example.grevocabularyapp.R;

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
        populateEbookData();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    private void populateEbookData() {
        // Prepare data
        //Dummy item cardview information one by one
        lst_Ebook_itemList = new ArrayList<>();
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_secure_messaging, "Secure Messaging Book", "This is the best biology book", R.drawable.ic_recycle, R.drawable.ic_repositories, R.drawable.biology_book));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_secure_messaging, "Secure Messaging Book","This is the best biology book", R.drawable.ic_recycle, R.drawable.ic_repositories, R.drawable.biology_book));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.expense_ledger_pen, "Professional Expense Management", "This is best management book",R.drawable.ic_qr_code, R.drawable.ic_calculator, R.drawable.ic_weather));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.camera, "Photography Pro", "Best Photography book ever in history", R.drawable.ic_about, R.drawable.ic_tuition_planner, R.drawable.ic_event_countdown));
        // Add more items as needed...
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_repositories, "Advanced Biology", "In-depth exploration of biology concepts", R.drawable.ic_recycle, R.drawable.ic_calculator, R.drawable.ic_weather));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_explore, "Finance Fundamentals", "Essential financial management principles", R.drawable.ic_qr_code, R.drawable.ic_about, R.drawable.ic_event_countdown));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.camera, "Mastering Photography", "Capture stunning images like a pro", R.drawable.ic_repositories, R.drawable.ic_explore, R.drawable.ic_calculator));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.biology_book, "Botany Basics", "Learn the essentials of plant biology", R.drawable.ic_about, R.drawable.ic_recycle, R.drawable.ic_event_countdown));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_alarm_clock, "Expense Ledger Guide", "Track and manage your expenses", R.drawable.ic_calculator, R.drawable.ic_weather, R.drawable.ic_tuition_planner));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.camera, "Photography for Beginners", "Basic skills for photography enthusiasts", R.drawable.ic_qr_code, R.drawable.ic_about, R.drawable.ic_weather));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_calculator, "Human Anatomy", "Detailed study of human body systems", R.drawable.ic_repositories, R.drawable.ic_recycle, R.drawable.ic_calculator));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_mess_management, "Investment Guide", "Smart investment strategies", R.drawable.ic_event_countdown, R.drawable.ic_explore, R.drawable.ic_qr_code));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.camera, "Creative Photography", "Inspire creativity with photography", R.drawable.ic_calculator, R.drawable.ic_about, R.drawable.ic_repositories));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.ic_tuition_planner, "Ecology Insights", "Understanding ecosystems and habitats", R.drawable.ic_weather, R.drawable.ic_explore, R.drawable.ic_tuition_planner));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.expense_ledger_pen, "Personal Finance", "Manage and plan your finances effectively", R.drawable.ic_qr_code, R.drawable.ic_recycle, R.drawable.ic_calculator));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.camera, "Photographic Storytelling", "Tell stories through photographs", R.drawable.ic_tuition_planner, R.drawable.ic_event_countdown, R.drawable.ic_weather));
        // Add more items as needed...

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
        } else if (item.getItemId() == R.id.nav_add_word) {
            startActivity(new Intent(EbookCollectionActivity.this, AddWordActivity.class));
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
