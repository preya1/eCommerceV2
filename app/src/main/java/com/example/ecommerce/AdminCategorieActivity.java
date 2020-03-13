package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

public class AdminCategorieActivity extends AppCompatActivity {

    private ImageView laptops,telefoane,ceasuri,casti;
    private ImageView pulover,sports,tricouri,haine_femei;
    private ImageView pantofi,ochelari,gradinarit,genti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_categorie);

        laptops = (ImageView)findViewById(R.id.laptopuri);
        telefoane =(ImageView)findViewById(R.id.telefoane);
        ceasuri = (ImageView)findViewById(R.id.ceasuri);
        casti = (ImageView)findViewById(R.id.casti);

        pulover = (ImageView)findViewById(R.id.pulover);
        sports = (ImageView)findViewById(R.id.tricouri_sport);
        tricouri = (ImageView)findViewById(R.id.tricouri);
        haine_femei = (ImageView)findViewById(R.id.haine_femei);

        pantofi = (ImageView)findViewById(R.id.pantofi);
        ochelari = (ImageView)findViewById(R.id.ochelari);
        gradinarit = (ImageView)findViewById(R.id.gradinarit);
        genti = (ImageView)findViewById(R.id.genti);

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                //Trebuie sa selectam categoria specifica in noua fereastra pentru a adauga un produs sub categoria respectiva
                intent.putExtra("categorii","laptops");
                startActivity(intent);
            }
        });

        telefoane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","telefoane");
                startActivity(intent);
            }
        });

        ceasuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","ceasuri");
                startActivity(intent);
            }
        });

        casti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","casti");
                startActivity(intent);
            }
        });

        pulover.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","pulover");
                startActivity(intent);
            }
        }));

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","sports");
                startActivity(intent);
            }
        });

        tricouri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","tricouri");
                startActivity(intent);
            }
        });

        haine_femei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","haine_femei");
                startActivity(intent);
            }
        });

        pantofi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
               intent.putExtra("categorii","pantofi");
               startActivity(intent);
            }
        });

        ochelari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
               intent.putExtra("categorii","ochelari");
               startActivity(intent);
            }
        });

        gradinarit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
               intent.putExtra("categorii","gradinarit");
               startActivity(intent);
            }
        });

        genti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategorieActivity.this,AdminAdaugaProdusActivity.class);
                intent.putExtra("categorii","genti");
            }
        });

    }
}
