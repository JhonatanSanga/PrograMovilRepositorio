package com.example.proyectoprogramovil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDatos> {

    ArrayList<String> listaDatos;

    public AdaptadorDatos(ArrayList<String> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override

    public AdaptadorDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementos_lista,null,false);
        return new ViewHolderDatos(view);
    }

    @Override

    public void onBindViewHolder(@NonNull AdaptadorDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaDatos.get(position));
    }


    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = itemView.findViewById(R.id.tvElemento);
        }

        public void asignarDatos(String s) {

            dato.setText(s);
        }
    }
}
