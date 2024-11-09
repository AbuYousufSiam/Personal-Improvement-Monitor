package com.ebook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.improvementmonitor.R;

public class AddActivity extends AppCompatActivity {
    EditText title_input, author_input, pages_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_book_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = title_input.getText().toString().trim();
                String author = author_input.getText().toString().trim();
                int pagesStr = Integer.parseInt(pages_input.getText().toString().trim());

                BookDatabaseHelper my_db = new BookDatabaseHelper(AddActivity.this);
                my_db.addBook(title, author, pagesStr);

                // Finish with RESULT_OK
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}