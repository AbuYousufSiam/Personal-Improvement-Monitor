package com.tracker.expense.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;

import com.example.improvementmonitor.R;

public class CategoryManagementActivity extends AppCompatActivity {

    private ListView listViewCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_management);

        listViewCategories = findViewById(R.id.listViewCategories);

        // TODO: Load and display categories, allow add/edit/delete
    }
}
