package com.example.improvementmonitor;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grevocabularyapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class EbookCollectionActivity extends AppCompatActivity {

//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle toggle;
//    private BottomNavigationView bottomNavigationView;

    private RecyclerView ebookTypeRecyclerView;
    private EbookTypeAdapter adp_EbookTypeAdapter;
    private List<EbookItem> lst_Ebook_itemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_types);

        //Initialize it after you set the view of the content
        ebookTypeRecyclerView = findViewById(R.id.id_EbookTypeRecyclerView);

        //Set layout manager to display items in a vertical list
        ebookTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare data
        lst_Ebook_itemList = new ArrayList<>();
        //Dummy item cardview information one by one
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

        // Set up adapter with data -- see the usages section after this files main class announcing -line 15 approximately
        adp_EbookTypeAdapter = new EbookTypeAdapter(this, lst_Ebook_itemList);
        ebookTypeRecyclerView.setAdapter(adp_EbookTypeAdapter);

        // Get BottomNavigationView and apply fade-in animation
//        View bottomNavOtherPages = findViewById(R.id.bottom_navigation_other_pages);
//        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//        bottomNavOtherPages.startAnimation(fadeIn);
//        bottomNavOtherPages.setVisibility(View.VISIBLE);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
