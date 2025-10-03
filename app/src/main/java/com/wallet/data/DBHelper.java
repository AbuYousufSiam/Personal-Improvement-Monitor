package com.wallet.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import android.database.Cursor;


import com.wallet.model.Wallet;
import com.wallet.model.SavingEntry;


import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "savings_app.db";
    private static final int DB_VERSION = 1;


    // Table names
    private static final String TABLE_WALLET = "wallet";
    private static final String TABLE_SAVINGS = "savings";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_WALLET + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "target_amount REAL NOT NULL, " +
                "saved_amount REAL DEFAULT 0" +
                ")");


        db.execSQL("CREATE TABLE " + TABLE_SAVINGS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "wallet_id INTEGER NOT NULL, " +
                "amount REAL NOT NULL, " +
                "note TEXT, " +
                "date TEXT DEFAULT (datetime('now','localtime')), " +
                "FOREIGN KEY(wallet_id) REFERENCES " + TABLE_WALLET + "(id) ON DELETE CASCADE" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WALLET);
        onCreate(db);
    }


    // --- Wallet operations ---
    public long addWallet(String name, double targetAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("target_amount", targetAmount);
        long id = db.insert(TABLE_WALLET, null, cv);
        db.close();
        return id;
    }


    public List<Wallet> getAllWallets() {
        List<Wallet> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, target_amount, saved_amount FROM " + TABLE_WALLET + " ORDER BY id DESC", null);
        if (c != null) {
            while (((android.database.Cursor) c).moveToNext()) {
                Wallet w = new Wallet(
                        c.getInt(0),
                        c.getString(1),
                        c.getDouble(2),
                        c.getDouble(3)
                );
                list.add(w);
            }
            c.close();
        }
        db.close();
        return list;
        }


public Wallet getWalletById(int walletId) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery("SELECT id, name, target_amount, saved_amount FROM " + TABLE_WALLET + " WHERE id = ?", new String[]{String.valueOf(walletId)});
    Wallet w = null;
    if (c != null) {
        if (c.moveToFirst()) {
            w = new Wallet(c.getInt(0), c.getString(1), c.getDouble(2), c.getDouble(3));
        }
        c.close();
    }
    db.close();
    return w;
}


// Recompute saved amount from savings table and update wallet.saved_amount column.
public double recomputeSavedAmount(int walletId) {
    double sum = 0;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery("SELECT SUM(amount) FROM " + TABLE_SAVINGS + " WHERE wallet_id = ?", new String[]{String.valueOf(walletId)});
    if (c != null) {
        if (c.moveToFirst()) {
            sum = c.isNull(0) ? 0 : c.getDouble(0);
        }
        c.close();
    }
// Update wallet saved_amount
    SQLiteDatabase wdb = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("saved_amount", sum);
    wdb.update(TABLE_WALLET, cv, "id = ?", new String[]{String.valueOf(walletId)});
    wdb.close();
    db.close();
    return sum;
}
// --- Savings operations ---
public long addSaving(int walletId, double amount, String note) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put("wallet_id", walletId);
    cv.put("amount", amount);
    cv.put("note", note);
    long id = db.insert(TABLE_SAVINGS, null, cv);
// Update stored saved_amount incrementally (also safe to call recomputeSavedAmount)
    db.execSQL("UPDATE " + TABLE_WALLET + " SET saved_amount = saved_amount + ? WHERE id = ?", new Object[]{amount, walletId});
    db.close();
    return id;
}


public List<SavingEntry> getSavingsForWallet(int walletId) {
    List<SavingEntry> list = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor c = db.rawQuery("SELECT id, wallet_id, amount, note, date FROM " + TABLE_SAVINGS + " WHERE wallet_id = ? ORDER BY id DESC", new String[]{String.valueOf(walletId)});
    if (c != null) {
        while (c.moveToNext()) {
            SavingEntry s = new SavingEntry(
                    c.getInt(0),
                    c.getInt(1),
                    c.getDouble(2),
                    c.getString(3),
                    c.getString(4)
            );
            list.add(s);
        }
        c.close();
    }
    db.close();
    return list;
}


// Optional: delete wallet and its savings
public int deleteWallet(int walletId) {
    SQLiteDatabase db = this.getWritableDatabase();
    int rows = db.delete(TABLE_WALLET, "id = ?", new String[]{String.valueOf(walletId)});
    db.close();
    return rows;
}
}