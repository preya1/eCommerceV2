package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button alaturareButon,logareButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alaturareButon = (Button) findViewById(R.id.buton_alaturare);
        logareButon = (Button) findViewById(R.id.buton_logare_principal);

        logareButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LogareActivity.class);
                startActivity(intent);
            }
        });

        alaturareButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InregistrareActivity.class);
                startActivity(intent);
            }
        });
        {

        }
    }
}
