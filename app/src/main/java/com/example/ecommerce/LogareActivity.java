package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecommerce.Model.Utilizatori;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogareActivity extends AppCompatActivity {

    private EditText IntroducereTelefon,IntroducereParola;
    private Button logareBtn;
    private ProgressDialog baraIncarcare;

    private String parentDbUtilizatori = "Utilizatori";
    private CheckBox chkAminteste;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean salvareLogare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logare);

        logareBtn = (Button)findViewById(R.id.logare);
        IntroducereTelefon = (EditText) findViewById(R.id.logare_telefon);
        IntroducereParola = (EditText) findViewById(R.id.logare_parola);
        chkAminteste = (CheckBox) findViewById(R.id.amintire_checkbox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        salvareLogare = loginPreferences.getBoolean("salvareLogare",false);
        if(salvareLogare == true)
        {
            IntroducereParola.setText(loginPreferences.getString("parola",""));
            IntroducereTelefon.setText(loginPreferences.getString("telefon",""));
            chkAminteste.setChecked(true);
        }

        baraIncarcare = new ProgressDialog(this);

        logareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String telefon = IntroducereTelefon.getText().toString();
                String parola =  IntroducereParola.getText().toString();

                if(chkAminteste.isChecked())
                {
                    loginPrefsEditor.putBoolean("salvareLogare",true);
                    loginPrefsEditor.putString("telefon",telefon);
                    loginPrefsEditor.putString("parola",parola);
                    loginPrefsEditor.commit();
                }
                else
                {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
                logareUtilizator();
            }

        //Verificam daca utilizatorul a marcat casuta Aminteste-ma

        });
}



    private void logareUtilizator()
    {
        String telefon = IntroducereTelefon.getText().toString();
        String parola =  IntroducereParola.getText().toString();

         if(TextUtils.isEmpty(telefon))
         {
               Toast.makeText(LogareActivity.this,"Te rog completeaza campul cu numarul de telefon",Toast.LENGTH_LONG).show();
         }

         else if(TextUtils.isEmpty(parola))
         {
               Toast.makeText(LogareActivity.this,"Te rog completeaza campul cu parola",Toast.LENGTH_LONG).show();
         }
         else
         {
             baraIncarcare.setTitle("Logare Cont");
             baraIncarcare.setMessage("Va rog asteptati cat timp va vom verifica datele");
             baraIncarcare.setCanceledOnTouchOutside(false);
             baraIncarcare.show();

             permiteAccess(telefon,parola);
         }
    }

    private void permiteAccess(final String telefon, final String parola)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        //Verificam daca utilizatorul este valabil sau Nu

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.child(parentDbUtilizatori).child(telefon).exists())
                {

                    Utilizatori dateUtilizator = dataSnapshot.child(parentDbUtilizatori).child(telefon).getValue(Utilizatori.class);

                    if(dateUtilizator.getTelefon().equals(telefon))
                    {

                        if(dateUtilizator.getParola().equals(parola))
                        {

                            Toast.makeText(LogareActivity.this,"Te-ai logat cu success!",Toast.LENGTH_LONG).show();
                            baraIncarcare.dismiss();

                            Intent intent = new Intent(LogareActivity.this,PaginaPrincipalaActivity.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(LogareActivity.this,"Parola gresita!",Toast.LENGTH_LONG).show();
                            baraIncarcare.dismiss();
                        }
                    }

                }
                else
                {
                    Toast.makeText(LogareActivity.this,"Contul cu numarul de telefon "  + telefon + " nu exista!",Toast.LENGTH_LONG).show();
                    baraIncarcare.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
