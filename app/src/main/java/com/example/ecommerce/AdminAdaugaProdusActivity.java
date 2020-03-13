package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminAdaugaProdusActivity extends AppCompatActivity {

    private String NumeCategorie;
    private ImageView imagineProdus;
    private Button butonProdus;
    private EditText introducereNumeProdus,introducereDescriereProdus,introducerePretProdus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adauga_produs);

        imagineProdus = (ImageView)findViewById(R.id.selectare_imagine_produs);
        butonProdus = (Button)findViewById(R.id.buton_adaugare_produs);

        introducereNumeProdus = (EditText)findViewById(R.id.nume_produs);
        introducereDescriereProdus = (EditText)findViewById(R.id.descriere_produs);
        introducerePretProdus = (EditText)findViewById(R.id.pret_produs);

        NumeCategorie = getIntent().getExtras().get("categorii").toString();



    }
}
