package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AdminAdaugaProdusActivity extends AppCompatActivity {

    private String NumeCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adauga_produs);

        NumeCategorie = getIntent().getExtras().get("categorii").toString();

        Toast.makeText(this,"NumeCategorie",Toast.LENGTH_LONG).show();
    }
}
