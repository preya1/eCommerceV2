package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AdminAdaugaProdusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adauga_produs);

        Toast.makeText(this, "Bine Ai Venit ! Admin !", Toast.LENGTH_SHORT).show();
    }
}
