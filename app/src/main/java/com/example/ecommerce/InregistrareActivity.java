package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InregistrareActivity extends AppCompatActivity {

    private Button CreareContButton;
    private EditText IntroducereNume,IntroducereTelefon,IntroducereParola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        CreareContButton = (Button)findViewById(R.id.inregistrare);
        IntroducereNume = (EditText) findViewById(R.id.inregistrare_nume);
        IntroducereTelefon = (EditText) findViewById(R.id.inregistrare_telefon);
        IntroducereParola = (EditText) findViewById(R.id.inregistrare_parola);

    }
}
