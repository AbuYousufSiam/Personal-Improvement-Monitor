package com.ebook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.grevocabularyapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    RecyclerView book_recyclerView;
    FloatingActionButton add_book_btn;
    SwipeRefreshLayout swipeRefreshLayout;

    BookDatabaseHelper myDB;
    ArrayList<String>book_id, book_title, book_author, book_pages;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        book_recyclerView = findViewById(R.id.s_book_recyclerview);
        swipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swiperlayout);
        add_book_btn = findViewById(R.id.add_book_float);
        add_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new BookDatabaseHelper(BookActivity.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

//      Calling the method
        storeDataInArrays();

        customAdapter = new CustomAdapter(BookActivity.this, this, book_id, book_title, book_author, book_pages);
        book_recyclerView.setAdapter(customAdapter);
        book_recyclerView.setLayoutManager(new LinearLayoutManager(BookActivity.this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myDB.readAllData();
                storeDataInArrays();
                customAdapter.notifyItemInserted();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();

        }else {
            customAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
        }
    }

    // user defined method
    private void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while(cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
        cursor.close();
    }
}