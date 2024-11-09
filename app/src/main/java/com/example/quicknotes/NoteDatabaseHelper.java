package com.example.quicknotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NoteDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTES = "notes";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PREVIEW = "preview";
    private static final String COLUMN_CATEGORY = "category";

    public NoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NOTES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_PREVIEW + " TEXT, " +
                COLUMN_CATEGORY + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    // Save note
    public void saveNote(String title, String preview, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_PREVIEW, preview);
        values.put(COLUMN_CATEGORY, category);
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    // Get all notes
    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTES, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String preview = cursor.getString(cursor.getColumnIndex(COLUMN_PREVIEW));
                String category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY));
                notes.add(new Note(id, title, preview, category));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return notes;
    }

    // Delete note
    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Update note
    public void updateNote(int id, String title, String preview, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_PREVIEW, preview);
        values.put(COLUMN_CATEGORY, category);
        db.update(TABLE_NOTES, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
