package com.example.improvementmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grevocabularyapp.R;

public class TaskManagementActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_management);

        // Get BottomNavigationView and apply fade-in animation
        View bottomNavOtherPages = findViewById(R.id.bottom_navigation_other_pages);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        bottomNavOtherPages.startAnimation(fadeIn);
        bottomNavOtherPages.setVisibility(View.VISIBLE);
    }
}
