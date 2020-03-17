package com.example.ecommerce.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Model.Produse;
import com.example.ecommerce.R;
import com.example.ecommerce.Vizualizare.ProdusVizualizare;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private DatabaseReference ProduseRef;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        ProduseRef = FirebaseDatabase.getInstance().getReference().child("Produse");

        FirebaseRecyclerOptions<Produse> options = new FirebaseRecyclerOptions.Builder<Produse>().setQuery(ProduseRef,Produse.class)
                .build();

        FirebaseRecyclerAdapter<Produse, ProdusVizualizare> adapter =
                new FirebaseRecyclerAdapter<Produse, ProdusVizualizare>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProdusVizualizare holder, int position, @NonNull Produse model)
                    {

                        holder.txtProdusNume.setText(model.getProdusnume());
                        holder.txtProdusPret.setText(model.getPret());
                        holder.txtProdusDescriere.setText("Pret = " + model.getDescriere() + "RON");
                        Picasso.get().load(model.getImagine()).into(holder.imagineView);
                    }

                    @NonNull
                    @Override
                    public ProdusVizualizare onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produse_design,parent,false);
                        ProdusVizualizare holder = new ProdusVizualizare(view);
                        return holder;
                    }
                };

           


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        return root;
    }
}
