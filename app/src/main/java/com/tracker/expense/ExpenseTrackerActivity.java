package com.tracker.expense;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.tracker.expense.activities.AddExpenseActivity;
import com.tracker.expense.activities.ViewExpensesActivity;
import com.tracker.expense.activities.ExpenseReportActivity;
import com.tracker.expense.activities.CategoryManagementActivity;
import com.tracker.expense.db.DatabaseHelper;
import com.tracker.expense.utils.ExpenseUtils;

import com.example.improvementmonitor.R;

public class ExpenseTrackerActivity extends AppCompatActivity {

    private CardView cardAddExpense, cardViewExpenses, cardExpenseReport, cardManageCategories;
    private TextView textTotalExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_tracker);

        // Initialize UI components
        cardAddExpense = findViewById(R.id.card_add_expense);
        cardViewExpenses = findViewById(R.id.card_view_expenses);
        cardExpenseReport = findViewById(R.id.card_expense_report);
        cardManageCategories = findViewById(R.id.card_manage_categories);
        textTotalExpenses = findViewById(R.id.text_total_expenses);

        // Set total expenses (assumes utility or DB logic is implemented)
        double total = ExpenseUtils.getTotalExpenses(this); // Implement in utils/db layer
        textTotalExpenses.setText("Amount = ৳ " + total);

        // Add Expense
        cardAddExpense.setOnClickListener(view -> {
            Intent intent = new Intent(ExpenseTrackerActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });

        // View Expenses
        cardViewExpenses.setOnClickListener(view -> {
            Intent intent = new Intent(ExpenseTrackerActivity.this, ViewExpensesActivity.class);
            startActivity(intent);
        });

        // Expense Report
        cardExpenseReport.setOnClickListener(view -> {
            Intent intent = new Intent(ExpenseTrackerActivity.this, ExpenseReportActivity.class);
            startActivity(intent);
        });

        // Manage Categories
        cardManageCategories.setOnClickListener(view -> {
            Intent intent = new Intent(ExpenseTrackerActivity.this, CategoryManagementActivity.class);
            startActivity(intent);
        });

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO expenses (amount, category, date) VALUES (500.0, 'Food', '2025-06-05')");
        db.close();

    }
}
