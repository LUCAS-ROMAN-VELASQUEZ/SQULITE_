package com.example.sqlite__;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "futbolistas.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movies (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT)");
        db.execSQL("CREATE TABLE Futbolistas (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad INTEGER, equipo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        db.execSQL("DROP TABLE IF EXISTS Futbolistas");
        onCreate(db);
    }

    public void clearDatabase(SQLiteDatabase db) {
        db.execSQL("DELETE FROM movies");
        db.execSQL("DELETE FROM Futbolistas");
    }
}