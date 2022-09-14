package com.estacionamento2d.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterVeicule extends RecyclerView.Adapter<AdapterVeicule.ViewHolderVeicule> {

    public static class ViewHolderVeicule extends RecyclerView.ViewHolder{

        public ViewHolderVeicule(@NonNull View itemView) {
            super(itemView);
        }
    }

    public AdapterVeicule(){

    }

    @NonNull
    @Override
    public ViewHolderVeicule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVeicule holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
