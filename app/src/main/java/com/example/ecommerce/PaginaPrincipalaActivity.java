package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaginaPrincipalaActivity extends AppCompatActivity {

private Button DelogareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principala);

        DelogareBtn = (Button) findViewById(R.id.buton_logout);
        DelogareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaginaPrincipalaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
