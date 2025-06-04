package com.tracker.expense.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.improvementmonitor.R;

public class ExpenseReportActivity extends AppCompatActivity {

    private TextView textViewReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_report);

        textViewReport = findViewById(R.id.textViewReport);

        // TODO: Generate and show expense report summary
        textViewReport.setText("Expense report summary will appear here.");
    }
}
