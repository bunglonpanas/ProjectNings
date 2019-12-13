package com.appinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TransaksiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
    }

    public void intentToAddBarang(View view)
    {
        Intent intent = new Intent(this, AddBarangActivity.class);
        startActivity(intent);
    }

}
