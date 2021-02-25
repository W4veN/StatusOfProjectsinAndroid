package com.example.projects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "Projects.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_projects";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE = "_type";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_DESCRIPTION = "_description";

     public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addProject (String type, String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); // przekazywanie wartosci dodanych do bazy

        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, cv); // przekazywanie wartosci do tabeli bazy
        if (result==-1 ) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfuly", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME; //wiersz
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!=null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String type, String name, String description) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues cv = new ContentValues();

         cv.put(COLUMN_TYPE, type);
         cv.put(COLUMN_NAME, name);
         cv.put(COLUMN_DESCRIPTION, description);

         long result = db.update(TABLE_NAME, cv, "_id=?", new String [] {row_id});
        if (result == -1 ) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
        }

    }

}
