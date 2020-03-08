package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button alaturareButon,logareButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alaturareButon = (Button) findViewById(R.id.buton_alaturare);
        logareButon = (Button) findViewById(R.id.buton_logare_principal);
    }
}
