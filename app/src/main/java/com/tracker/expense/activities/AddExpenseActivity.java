package com.tracker.expense.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.improvementmonitor.R;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText editTextAmount, editTextCategory, editTextDescription;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        editTextAmount = findViewById(R.id.editTextAmount);
        editTextCategory = findViewById(R.id.editTextCategory);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Simple validation and save logic placeholder
                String amount = editTextAmount.getText().toString();
                String category = editTextCategory.getText().toString();

                if (amount.isEmpty() || category.isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Save expense to database
                    Toast.makeText(AddExpenseActivity.this, "Expense saved successfully", Toast.LENGTH_SHORT).show();
                    finish();  // Close activity after saving
                }
            }
        });
    }
}
