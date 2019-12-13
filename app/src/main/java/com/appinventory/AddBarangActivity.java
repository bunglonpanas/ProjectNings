package com.appinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.appinventory.Helper.SQLiteHelper;

public class AddBarangActivity extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper mSQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);

        mSQLiteHelper = new SQLiteHelper(this, "RECORDDB.sqlite", null, 1);
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, safetyStock INTEGER, optimumStock INTEGER, currentStock INTEGER, " +
                "price VARCHAR, note VARCHAR,tags VARCHAR,image BLOB)");
    }
}
