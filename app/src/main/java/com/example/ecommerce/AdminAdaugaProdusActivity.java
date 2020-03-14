package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class AdminAdaugaProdusActivity extends AppCompatActivity {

    private String NumeCategorie,Descriere,numeProdus,Pret,salveazaDataCurenta,salveazaOraCurenta;
    private ImageView imagineProdus;
    private Button butonProdus;
    private EditText introducereNumeProdus,introducereDescriereProdus,introducerePretProdus;
    private static final int GaleriePic = 1;
    private Uri ImagineUri;
    private String randomProdusKey,descarcaImagineUrl;
    private StorageReference imaginiProdusRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adauga_produs);

        imagineProdus = (ImageView)findViewById(R.id.selectare_imagine_produs);
        butonProdus = (Button)findViewById(R.id.buton_adaugare_produs);

        introducereNumeProdus = (EditText)findViewById(R.id.nume_produs);
        introducereDescriereProdus = (EditText)findViewById(R.id.descriere_produs);
        introducerePretProdus = (EditText)findViewById(R.id.pret_produs);

        imagineProdus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                deschideGalerie();
            }
        });

        butonProdus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ValidareDateProdus();
            }
        });

        NumeCategorie = getIntent().getExtras().get("categorii").toString();

        //Cream un folder de stochare imagini in Firebase
        imaginiProdusRef = FirebaseStorage.getInstance().getReference().child("Imagini Produs");


    }



    private void deschideGalerie()
    {
        Intent intentGalerie = new Intent();
        intentGalerie.setAction(Intent.ACTION_GET_CONTENT);
        intentGalerie.setType("imagine/*");
        startActivityForResult(intentGalerie,GaleriePic);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GaleriePic && resultCode == RESULT_OK && data!=null)
        {
               ImagineUri = data.getData();
               imagineProdus.setImageURI(ImagineUri);
        }
    }

    private void ValidareDateProdus()
    {
        Descriere = introducereDescriereProdus.getText().toString();
        Pret = introducerePretProdus.getText().toString();
        numeProdus = introducereNumeProdus.getText().toString();

        //Daca utilizatorul nu selecteaza imaginea produsului
        if(ImagineUri == null)
        {
           Toast.makeText(this,"Nu ati incarcat nici o imagine",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(Descriere))
        {
            Toast.makeText(this,"Va rog puneti o descriere",Toast.LENGTH_LONG).show();
        }
        else if (TextUtils.isEmpty(Pret))
        {
            Toast.makeText(this,"Nu ati stabilit un pret",Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(numeProdus))
        {
            Toast.makeText(this,"Nu ati introdus un nume pentru produs",Toast.LENGTH_LONG).show();
        }
        else
        {
            stocheazaInformatiiProdus();
        }
        

}

   //Stocheaza informatii despre produs

    private void stocheazaInformatiiProdus()
    {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dataCurenta = new SimpleDateFormat("MMM dd, yyyy");
        salveazaDataCurenta = dataCurenta.format(calendar.getTime());

        SimpleDateFormat dataTimp = new SimpleDateFormat("HH:mm:ss a");
        salveazaOraCurenta = dataTimp.format(calendar.getTime());

        //Creare Cheie unica Random pentru produs;

        randomProdusKey = salveazaDataCurenta + salveazaOraCurenta;

        //Stocheaza Imagine Produs in Firebase

        final StorageReference caleFisier = imaginiProdusRef.child(ImagineUri.getLastPathSegment() + randomProdusKey);

        final UploadTask uploadTask = caleFisier.putFile(ImagineUri);

        //In caz de apare o eroare la incarcare Imagine

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String mesaj = e.toString();
                Toast.makeText(AdminAdaugaProdusActivity.this,"Eroare : " + mesaj,Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(AdminAdaugaProdusActivity.this,"Imagine actualizata cu succes",Toast.LENGTH_LONG).show();

                //STOCHARE LINK IMAGINE IN BAZA DE DATE FIREBASE

                Task<Uri>urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if(!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                           descarcaImagineUrl = caleFisier.getDownloadUrl().toString();
                           return caleFisier.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AdminAdaugaProdusActivity.this,"Am primit imagine produs",Toast.LENGTH_LONG).show();

                            salvareProdusInfoBD();
                        }
                    }
                });
            }
        });
    }

    private void salvareProdusInfoBD()
    {
        
    }
}

