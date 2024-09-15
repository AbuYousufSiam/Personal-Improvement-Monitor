package com.example.grevocabularyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grevocabularyapp.MyDatabaseHelper;

public class AddWordActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private EditText wordEditText, meaningEditText;
    private Button addWordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        dbHelper = new MyDatabaseHelper(this); // Initialize the database helper
        wordEditText = findViewById(R.id.wordEditText);
        meaningEditText = findViewById(R.id.meaningEditText);
        addWordButton = findViewById(R.id.addWordButton);

        // Set a click listener for the button
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEditText.getText().toString();
                String meaning = meaningEditText.getText().toString();

                if (!word.isEmpty() && !meaning.isEmpty()) {
                    dbHelper.insertWord(word, meaning); // Insert the word into the database
                    Toast.makeText(AddWordActivity.this, "Word added!", Toast.LENGTH_SHORT).show();

                    // Clear the input fields after adding
                    wordEditText.setText("");
                    meaningEditText.setText("");
                } else {
                    Toast.makeText(AddWordActivity.this, "Please enter both word and meaning.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
