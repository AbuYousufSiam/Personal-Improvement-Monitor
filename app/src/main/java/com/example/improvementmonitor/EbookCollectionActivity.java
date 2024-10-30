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
        lst_Ebook_itemList.add(new EbookItem(R.drawable.biology_book, "Biology Book", R.drawable.ic_recycle, R.drawable.ic_repositories, R.drawable.ic_explore));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.expense_ledger_pen, "Learn Professional Expense Management", R.drawable.ic_qr_code, R.drawable.ic_calculator, R.drawable.ic_weather));
        lst_Ebook_itemList.add(new EbookItem(R.drawable.camera, "Photography Pro", R.drawable.ic_about, R.drawable.ic_tuition_planner, R.drawable.ic_event_countdown));
        // Add more items as needed...

        // Set up adapter with data -- see the usages section after this files main class announcing -line 15 approximately
        adp_EbookTypeAdapter = new EbookTypeAdapter(this, lst_Ebook_itemList);
        ebookTypeRecyclerView.setAdapter(adp_EbookTypeAdapter);

        // Get BottomNavigationView and apply fade-in animation
        View bottomNavOtherPages = findViewById(R.id.bottom_navigation_other_pages);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        bottomNavOtherPages.startAnimation(fadeIn);
        bottomNavOtherPages.setVisibility(View.VISIBLE);

    }
}
