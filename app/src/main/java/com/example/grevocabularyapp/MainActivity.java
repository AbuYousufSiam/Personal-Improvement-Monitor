package com.example.grevocabularyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WordAdapter adapter;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.wordRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the database helper
        dbHelper = new MyDatabaseHelper(this);

        // Fetch words from the database and set them in the RecyclerView
        loadWordsFromDatabase();

        // Initialize the "Add Word" button
        Button openAddWordActivityButton = findViewById(R.id.openAddWordActivityButton);

        // Set a click listener to open AddWordActivity
        openAddWordActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddWordActivity when the button is clicked
                Intent intent = new Intent(MainActivity.this, AddWordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload the word list whenever the activity is resumed (after adding new words)
        loadWordsFromDatabase();
    }

    // Method to load words from the database and set them to the RecyclerView
    private void loadWordsFromDatabase() {
        List<Word> wordList = dbHelper.getAllWords();  // Fetch all words from the database
        adapter = new WordAdapter(wordList);  // Create a new adapter with the word list
        recyclerView.setAdapter(adapter);  // Set the adapter to the RecyclerView
    }
}
