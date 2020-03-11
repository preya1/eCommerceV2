package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InregistrareActivity extends AppCompatActivity {

    private Button CreareContButton;
    private EditText IntroducereNume,IntroducereTelefon,IntroducereParola;
    private ProgressDialog baraIncarcare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        CreareContButton = (Button)findViewById(R.id.inregistrare);
        IntroducereNume = (EditText) findViewById(R.id.inregistrare_nume);
        IntroducereTelefon = (EditText) findViewById(R.id.inregistrare_telefon);
        IntroducereParola = (EditText) findViewById(R.id.inregistrare_parola);
        baraIncarcare = new ProgressDialog(this);

        CreareContButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreareCont();
            }
        });
    }

    private void CreareCont() {
        String nume = IntroducereNume.getText().toString();
        String telefon = IntroducereTelefon.getText().toString();
        String parola =  IntroducereParola.getText().toString();

        //Verificare daca textul este gol

        if(TextUtils.isEmpty(nume))
        {
            Toast.makeText(InregistrareActivity.this,"Te rog completeaza campul cu numele tau",Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(telefon))
        {
            Toast.makeText(InregistrareActivity.this,"Te rog completeaza campul cu numarul de telefon",Toast.LENGTH_LONG).show();
        }

        else if(TextUtils.isEmpty(parola))
        {
            Toast.makeText(InregistrareActivity.this,"Te rog completeaza campul cu parola",Toast.LENGTH_LONG).show();
        }
        else
        {
            baraIncarcare.setTitle("Creare Cont");
            baraIncarcare.setMessage("Va rog asteptati cat timp va vom verifica datele");
            baraIncarcare.setCanceledOnTouchOutside(false);
            baraIncarcare.show();

            ValidareTelefon(nume,telefon,parola);
        }


    }

    private void ValidareTelefon(String nume, String telefon, String parola)
    {

    }
}
