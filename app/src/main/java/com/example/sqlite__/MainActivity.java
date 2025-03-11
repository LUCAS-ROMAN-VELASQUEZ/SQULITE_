package com.example.sqlite__;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button insertButton, deleteButton, listButton;
    private MovieDatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    private List<String> futbolistasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        insertButton = findViewById(R.id.insertButton);
        deleteButton = findViewById(R.id.deleteButton);
        listButton = findViewById(R.id.listButton);

        dbHelper = new MovieDatabaseHelper(this);
        futbolistasList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, futbolistasList);
        listView.setAdapter(adapter);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertFutbolistas();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllFutbolistas();
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listFutbolistas();
            }
        });
    }

    private void insertFutbolistas() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.clearDatabase(db);

        String[] Futbolistas = {"Leo Messi", "Rapinha", "Gavi", "Lamine Yamal", "Dani Guiza", "Ansu Fati"};
        for (String nombre : Futbolistas) {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            db.insert("Futbolistas", null, values);
        }
        db.close();
    }

    private void deleteAllFutbolistas() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.clearDatabase(db);
        db.close();
        futbolistasList.clear();
        adapter.notifyDataSetChanged();
    }

    private void listFutbolistas() {
        futbolistasList.clear();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nombre FROM Futbolistas", null);
        if (cursor.moveToFirst()) {
            do {
                futbolistasList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        adapter.notifyDataSetChanged();
    }
}