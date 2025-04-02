package com.example.nakupnseznam_ronkovprc;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class EditItemActivity extends Activity {
    private EditText itemName;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        dbHelper = new DatabaseHelper(this);

        // Inicializace UI prvků
        itemName = findViewById(R.id.itemName);
        Button saveButton = findViewById(R.id.saveButton);
        ListView itemListView = findViewById(R.id.itemListView);

        // Uložení změn do databáze
        saveButton.setOnClickListener(v -> {
            String name = itemName.getText().toString();
            updateItemInDatabase(name);
        });
    }

    private void updateItemInDatabase(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "UPDATE " + DatabaseHelper.TABLE_ITEMS + " SET " +
                DatabaseHelper.COLUMN_NAME + " = '" + name + "' WHERE " +
                DatabaseHelper.COLUMN_STATUS + " = 'pending'";
        db.execSQL(sql);
    }
}
