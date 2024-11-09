package com.example.improvementmonitor;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.improvementmonitor.R;

public class AddWordActivity extends AppCompatActivity {

    private EditText wordEditText;
    private EditText meaningEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        wordEditText = findViewById(R.id.editTextWord);
        meaningEditText = findViewById(R.id.editTextMeaning);
    }

    public void addWord(View view) {
        String word = wordEditText.getText().toString();
        String meaning = meaningEditText.getText().toString();

        if (word.isEmpty() || meaning.isEmpty()) {
            Toast.makeText(this, "Please enter both word and meaning", Toast.LENGTH_SHORT).show();
            return;
        }

        // Here you would add code to save the word and meaning to your database or other storage.

        Toast.makeText(this, "Word added successfully", Toast.LENGTH_SHORT).show();
        finish(); // Close the activity and return to previous screen
    }
}
