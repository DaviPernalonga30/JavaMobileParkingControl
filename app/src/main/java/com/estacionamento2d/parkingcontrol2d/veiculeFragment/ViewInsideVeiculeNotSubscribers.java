package com.estacionamento2d.parkingcontrol2d.veiculeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.estacionamento2d.adapter.AdapterVeicule;
import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.VeiculeClass;
import com.estacionamento2d.parkingcontrol2d.R;

import java.util.ArrayList;

public class ViewInsideVeiculeNotSubscribers extends Fragment {

    public ViewInsideVeiculeNotSubscribers() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_inside_veicule_not_subscribers, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_inside_avulsos);
        Button button_inside_avulsos_homepage = view.findViewById(R.id.button_inside_avulsos_homepage);

        ArrayList<VeiculeClass> aux1 = new DataBaseManagement().selectFromVeicule();
        ArrayList<VeiculeClass> aux2 = new ArrayList<>();
        for(int i = 0; i<aux1.size(); i=i+1){
            if(aux1.get(i).getIsSubscriber() == false){
                aux2.add(aux1.get(i));
            }

        }

        recyclerView.setAdapter(new AdapterVeicule(aux2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        button_inside_avulsos_homepage.setText("Voltar.");
        button_inside_avulsos_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_viewInsideVeiculeNotSubscribers_to_homePageFragment);
            }
        });





        return view;
    }
}