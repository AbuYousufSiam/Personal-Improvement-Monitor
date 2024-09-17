package com.example.improvementmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "vocabApp.db";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating the table when database is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS_TABLE = "CREATE TABLE words (id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT, meaning TEXT)";
        db.execSQL(CREATE_WORDS_TABLE); // Execute the SQL statement
    }

    // Handling database upgrades
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS words"); // Drop older table if exists
        onCreate(db); // Create table again
    }
    // Method to insert a word into the database
    public void insertWord(String word, String meaning) {
        SQLiteDatabase db = this.getWritableDatabase(); // Open the database for writing
        ContentValues values = new ContentValues();

        values.put("word", word);  // Add the word
        values.put("meaning", meaning);  // Add the meaning

        db.insert("words", null, values); // Insert the word and meaning into the words table
        db.close(); // Close the database connection
    }

    public List<Word> getAllWords() {
        List<Word> wordList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();  // Open the database for reading
        Cursor cursor = db.rawQuery("SELECT * FROM words", null);  // Get all rows from the words table

        if (cursor.moveToFirst()) {
            do {
                String word = cursor.getString(cursor.getColumnIndexOrThrow("word"));
                String meaning = cursor.getString(cursor.getColumnIndexOrThrow("meaning"));
                wordList.add(new Word(word, meaning));  // Add each word and meaning to the list
            } while (cursor.moveToNext());
        }
        cursor.close();  // Close the cursor
        db.close();  // Close the database

        return wordList;
    }


}
