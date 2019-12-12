package com.appinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnLoginAdmin;
    CheckBox chkbx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        chkbx = findViewById(R.id.chkboxLogin);

        chkbx.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    btnLogin.setVisibility(View.GONE);
                    btnLoginAdmin.setVisibility(View.VISIBLE);
                }
                else
                {
                    btnLogin.setVisibility(View.VISIBLE);
                    btnLoginAdmin.setVisibility(View.GONE);
                }
                //case 2

            }
        });
        // RESPON UNTUK AKSI KLIK PADA TOMBOL

    }
    public void intentToDashboard(View view)
    {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
    public void intentToDashboardAdmin(View view)
    {
        Intent intent = new Intent(this, DashboardAdminActivity.class);
        startActivity(intent);
    }


}
