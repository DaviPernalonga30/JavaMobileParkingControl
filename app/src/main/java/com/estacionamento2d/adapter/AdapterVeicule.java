package com.estacionamento2d.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estacionamento2d.javasrc.VeiculeClass;

public class AdapterVeicule extends RecyclerView.Adapter<AdapterVeicule.ViewHolderVeicule> {
    private final java.util.ArrayList<VeiculeClass> localdata;

    public static class ViewHolderVeicule extends RecyclerView.ViewHolder{

        public ViewHolderVeicule(@NonNull View itemView) {
            super(itemView);


            //Colocar as variáveis aqui
            //Variaveis essas de texto





        }
    }

    public AdapterVeicule(java.util.ArrayList veicule){
        this.localdata = veicule;

    }

    @NonNull
    @Override
    public ViewHolderVeicule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
        //inflater
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVeicule holder, int position) {
        //fazer a atribuição das variaveis com a localdata.

    }

    @Override
    public int getItemCount() {
        return localdata.size();
    }




}
