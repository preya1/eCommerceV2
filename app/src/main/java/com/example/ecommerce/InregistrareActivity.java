package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

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

    private void ValidareTelefon(final String nume, final String telefon, final String parola)
    {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("Utilizatori").child(telefon).exists()))
                {

                    HashMap<String,Object> dateUtilizatorMap = new HashMap<>();
                    dateUtilizatorMap.put("telefon",telefon);
                    dateUtilizatorMap.put("nume",nume);
                    dateUtilizatorMap.put("parola",parola);

                    RootRef.child("Utilizatori").child(telefon).updateChildren(dateUtilizatorMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                               if(task.isSuccessful())
                               {
                                   Toast.makeText(InregistrareActivity.this,"Felicitari ! Cont creat cu succes!",Toast.LENGTH_LONG).show();
                                   baraIncarcare.dismiss();

                                   Intent intent = new Intent(InregistrareActivity.this,LogareActivity.class);
                                   startActivity(intent);
                               }
                               else
                               {
                                   baraIncarcare.dismiss();
                                   Toast.makeText(InregistrareActivity.this,"O problema de retea ! Va rugam reincercati !",Toast.LENGTH_LONG).show();
                               }
                        }
                    });

                }
                else
                {
                    Toast.makeText(InregistrareActivity.this,"Ne pare rau numarul de telefon este deja inregistrat!",Toast.LENGTH_LONG).show();
                    baraIncarcare.dismiss();
                    Toast.makeText(InregistrareActivity.this,"Te rog incearca un alt numar de telefon",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
