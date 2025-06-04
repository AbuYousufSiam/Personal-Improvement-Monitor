package com.tracker.expense.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.improvementmonitor.R;

public class ViewExpensesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        recyclerViewExpenses = findViewById(R.id.recyclerViewExpenses);
        recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Set adapter with expense list
        // recyclerViewExpenses.setAdapter(new ExpensesAdapter(yourExpenseList));
    }
}
