package com.estacionamento2d.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.Subscriber;
import com.estacionamento2d.javasrc.VeiculeClass;
import com.estacionamento2d.parkingcontrol2d.R;

public class AdapterVeicule extends RecyclerView.Adapter<AdapterVeicule.ViewHolderVeicule> {
    private final java.util.ArrayList<VeiculeClass> localdata;

    public static class ViewHolderVeicule extends RecyclerView.ViewHolder{
        private final TextView veicule_license;
        private final TextView veicule_timein;
        private final TextView veicule_timeout;
        private final TextView veicule_mensalist_name;
        private final TextView veicule_ismensalist;
        private final TextView veicule_ismotorbike;
        private final TextView veicule_haskey;

        public ViewHolderVeicule(@NonNull View itemView) {
            super(itemView);

            this.veicule_license = (TextView) itemView.findViewById(R.id.veicule_license);
            this.veicule_timein = (TextView) itemView.findViewById(R.id.veicule_timein);
            this.veicule_timeout = (TextView) itemView.findViewById(R.id.veicule_timeout);
            this.veicule_mensalist_name = (TextView) itemView.findViewById(R.id.veicule_mensalist_name);
            this.veicule_ismensalist = (TextView) itemView.findViewById(R.id.veicule_ismensalist);
            this.veicule_ismotorbike = (TextView) itemView.findViewById(R.id.veicule_ismotorbike);
            this.veicule_haskey = (TextView) itemView.findViewById(R.id.veicule_haskey);

        }


        public TextView getVeicule_license() {
            return veicule_license;
        }
        public TextView getVeicule_timein() {
            return veicule_timein;
        }
        public TextView getVeicule_timeout() {
            return veicule_timeout;
        }
        public TextView getVeicule_mensalist_name() {
            return veicule_mensalist_name;
        }
        public TextView getVeicule_ismensalist() {
            return veicule_ismensalist;
        }
        public TextView getVeicule_ismotorbike() {
            return veicule_ismotorbike;
        }
        public TextView getVeicule_haskey(){
            return veicule_haskey;
        }
    }

    public AdapterVeicule(java.util.ArrayList veicule){
        this.localdata = veicule;

    }

    @NonNull
    @Override
    public ViewHolderVeicule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.veicule_item, parent, false);

        return new AdapterVeicule.ViewHolderVeicule(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVeicule holder, int position) {
        holder.getVeicule_license().setText(localdata.get(position).getLicense());
        holder.getVeicule_timein().setText(localdata.get(position).getTimeIn());
        holder.getVeicule_timeout().setText(localdata.get(position).getTimeOut());
        holder.getVeicule_ismensalist().setText(String.valueOf("Mensalista: "+localdata.get(position).getIsSubscriber()));
        holder.getVeicule_ismotorbike().setText(String.valueOf("Moto: "+localdata.get(position).getIsMotorBike()));
        holder.getVeicule_haskey().setText(String.valueOf("Chave: "+localdata.get(position).getHasKey()));

        holder.getVeicule_mensalist_name().setText("");
    }

    @Override
    public int getItemCount() {
        return localdata.size();
    }




}
