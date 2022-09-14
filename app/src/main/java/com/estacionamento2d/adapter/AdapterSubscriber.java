package com.estacionamento2d.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estacionamento2d.javasrc.Subscriber;
import com.estacionamento2d.parkingcontrol2d.R;

public class AdapterSubscriber extends RecyclerView.Adapter<AdapterSubscriber.ViewHolderSubs>{

    private java.util.ArrayList<Subscriber> localdata;


    public static class ViewHolderSubs extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView start_date;
        private final TextView end_date;
        private final TextView license;
        private final TextView contact;
        private final TextView isMensalist;



        public ViewHolderSubs(@NonNull View itemView) {
            super(itemView);

            this.name = (TextView) itemView.findViewById(R.id.mensalist_name);
            this.start_date = (TextView) itemView.findViewById(R.id.mensalist_start_date);
            this.end_date = (TextView) itemView.findViewById(R.id.mensalist_end_date);
            this.license = (TextView) itemView.findViewById(R.id.mensalist_license);
            this.contact = (TextView) itemView.findViewById(R.id.mensalist_contact);
            this.isMensalist = (TextView) itemView.findViewById(R.id.mensalist_ismensalist);


        }

        public TextView getName(){
            return this.name;
        }
        public TextView getStart_date(){
            return this.start_date;
        }
        public TextView getEnd_date(){
            return this.end_date;
        }
        public TextView getLicense(){
            return this.license;
        }
        public TextView getContact(){
            return this.contact;
        }
        public TextView getIsMensalist(){
            return this.isMensalist;
        }





    }



    public AdapterSubscriber(java.util.ArrayList sub){
        this.localdata = sub;
    }


    @NonNull
    @Override
    public ViewHolderSubs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mensalist_item, parent, false);

        return new ViewHolderSubs(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSubs holder, int position) {


        holder.getName().setText(localdata.get(position).getName());
        holder.getStart_date().setText(localdata.get(position).getSubscriptionDate());
        holder.getEnd_date().setText(localdata.get(position).getSubscriptionDeadLine());
        holder.getLicense().setText(localdata.get(position).getLicense());
        holder.getContact().setText(localdata.get(position).getContact());
        if(localdata.get(position).getIsMensalist() == true){
            holder.getIsMensalist().setText("Mensalidade em dia");
        }
        else{
            holder.getIsMensalist().setText("Mensalidade atrasada");
        }



    }

    @Override
    public int getItemCount() {
        return localdata.size();
    }



}
