package com.example.nakupnseznam_ronkovprc;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;



public class MainActivity extends Activity {
    private EditText itemName;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        // Inicializace prvků UI
        itemName = findViewById(R.id.itemName);
        ImageView itemImage = findViewById(R.id.itemImage);
        Button addButton = findViewById(R.id.addButton);
        Button goToEditButton = findViewById(R.id.goToEditButton);
        Button goToHistoryButton = findViewById(R.id.goToHistoryButton);

        // Nastavení klikací akce pro přidání položky
        addButton.setOnClickListener(v -> {
            String name = itemName.getText().toString();
            // Cesta k obrázku, tuto hodnotu byste získali z kamery
            String imagePath = "path_to_image";
            addItemToDatabase(name, imagePath);
        });

        // Přepnutí na druhou obrazovku pro úpravu
        goToEditButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
            startActivity(intent);
        });

        // Přepnutí na třetí obrazovku pro historii
        goToHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PurchaseHistoryActivity.class);
            startActivity(intent);
        });
    }

    // Funkce pro přidání položky do databáze
    private void addItemToDatabase(String name, String imagePath) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "INSERT INTO " + DatabaseHelper.TABLE_ITEMS + " (" +
                DatabaseHelper.COLUMN_NAME + ", " +
                DatabaseHelper.COLUMN_IMAGE_PATH + ", " +
                DatabaseHelper.COLUMN_STATUS + ") VALUES ('" +
                name + "', '" + imagePath + "', 'pending')";
        db.execSQL(sql);
    }
}
