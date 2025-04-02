package com.example.nakupnseznam_ronkovprc;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class PurchaseHistoryActivity extends Activity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        dbHelper = new DatabaseHelper(this);

        // Inicializace UI prvků
        ListView historyListView = findViewById(R.id.historyListView);
        Button deleteButton = findViewById(R.id.deleteButton);

        // Smazání položky z historie
        deleteButton.setOnClickListener(v -> deleteItemFromDatabase());
    }

    private void deleteItemFromDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM " + DatabaseHelper.TABLE_ITEMS + " WHERE " +
                DatabaseHelper.COLUMN_STATUS + " = 'purchased'";
        db.execSQL(sql);
    }
}
