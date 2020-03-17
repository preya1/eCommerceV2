package com.example.ecommerce.Vizualizare;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.InterfataProduse.ItemClickListener;
import com.example.ecommerce.R;

public class ProdusVizualizare extends RecyclerView.ViewHolder implements View.OnClickListener
{
    //Accesare

    public TextView txtProdusNume,txtProdusDescriere,txtProdusPret;
    public ImageView imagineView;
    public ItemClickListener listener;

    public ProdusVizualizare(@NonNull View itemView) {
        super(itemView);

        imagineView = (ImageView)itemView.findViewById(R.id.produs_imagine);
        txtProdusNume = (TextView)itemView.findViewById(R.id.nume_produs);
        txtProdusDescriere = (TextView)itemView.findViewById(R.id.produs_descriere);
        txtProdusPret = (TextView)itemView.findViewById(R.id.produs_pret);
    }

    public void setItemClickListener(ItemClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v)
    {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
