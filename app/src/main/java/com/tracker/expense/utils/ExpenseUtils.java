package com.tracker.expense.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tracker.expense.db.DatabaseHelper;

public class ExpenseUtils {

    public static double getTotalExpenses(Context context) {
        double total = 0.0;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT SUM(" + DatabaseHelper.COL_AMOUNT + ") FROM " + DatabaseHelper.TABLE_EXPENSES;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null && cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        if (cursor != null) cursor.close();
        db.close();

        return total;
    }
}
