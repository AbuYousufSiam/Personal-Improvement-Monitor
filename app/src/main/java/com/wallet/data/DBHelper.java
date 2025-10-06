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

    // ------------------------------
    // WALLET OPERATIONS
    // ------------------------------

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
            while (c.moveToNext()) {
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
        Wallet wallet = null;

        if (c != null && c.moveToFirst()) {
            wallet = new Wallet(c.getInt(0), c.getString(1), c.getDouble(2), c.getDouble(3));
            c.close();
        }
        db.close();
        return wallet;
    }

    public double recomputeSavedAmount(int walletId) {
        double total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(amount) FROM " + TABLE_SAVINGS + " WHERE wallet_id = ?", new String[]{String.valueOf(walletId)});

        if (c != null) {
            if (c.moveToFirst()) {
                total = c.isNull(0) ? 0 : c.getDouble(0);
            }
            c.close();
        }

        SQLiteDatabase writableDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("saved_amount", total);
        writableDb.update(TABLE_WALLET, cv, "id = ?", new String[]{String.valueOf(walletId)});
        writableDb.close();
        db.close();

        return total;
    }

    // ------------------------------
    // SAVINGS OPERATIONS
    // ------------------------------

    public long addSaving(int walletId, double amount, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("wallet_id", walletId);
        cv.put("amount", amount);
        cv.put("note", note);

        long id = db.insert(TABLE_SAVINGS, null, cv);

        // Increment saved_amount in wallet table
        db.execSQL("UPDATE " + TABLE_WALLET + " SET saved_amount = saved_amount + ? WHERE id = ?",
                new Object[]{amount, walletId});

        db.close();
        return id;
    }

    public List<SavingEntry> getSavingsForWallet(int walletId) {
        List<SavingEntry> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, wallet_id, amount, note, date FROM " + TABLE_SAVINGS +
                " WHERE wallet_id = ? ORDER BY id DESC", new String[]{String.valueOf(walletId)});

        if (c != null) {
            while (c.moveToNext()) {
                list.add(new SavingEntry(
                        c.getInt(0),
                        c.getInt(1),
                        c.getDouble(2),
                        c.getString(3),
                        c.getString(4)
                ));
            }
            c.close();
        }
        db.close();
        return list;
    }

    public SavingEntry getSavingById(int savingId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, wallet_id, amount, note, date FROM " + TABLE_SAVINGS + " WHERE id = ?",
                new String[]{String.valueOf(savingId)});

        SavingEntry s = null;
        if (c != null && c.moveToFirst()) {
            s = new SavingEntry(
                    c.getInt(0),
                    c.getInt(1),
                    c.getDouble(2),
                    c.getString(3),
                    c.getString(4)
            );
            c.close();
        }
        db.close();
        return s;
    }

    public int updateSaving(int savingId, double newAmount, String newNote) {
        SavingEntry oldEntry = getSavingById(savingId);
        if (oldEntry == null) return 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("amount", newAmount);
        cv.put("note", newNote);

        int rows = db.update(TABLE_SAVINGS, cv, "id = ?", new String[]{String.valueOf(savingId)});
        db.close();

        recomputeSavedAmount(oldEntry.getWalletId());
        return rows;
    }

    public int deleteSaving(int savingId) {
        SavingEntry s = getSavingById(savingId);
        if (s == null) return 0;

        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_SAVINGS, "id = ?", new String[]{String.valueOf(savingId)});
        db.close();

        recomputeSavedAmount(s.getWalletId());
        return rows;
    }

    public int deleteWallet(int walletId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_WALLET, "id = ?", new String[]{String.valueOf(walletId)});
        db.close();
        return rows;
    }
}
