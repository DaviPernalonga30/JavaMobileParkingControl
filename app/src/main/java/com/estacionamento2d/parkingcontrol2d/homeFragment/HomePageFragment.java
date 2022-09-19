package com.estacionamento2d.parkingcontrol2d.homeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.estacionamento2d.parkingcontrol2d.R;


public class HomePageFragment extends Fragment {
    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        //TextView Declarations


        //TextView setText



        //Buttons declarations
        Button buttonAddVeiculeFromSubscriber = (Button) view.findViewById(R.id.button_add_veicule_from_subscriber);



        //buttons setText
        buttonAddVeiculeFromSubscriber.setText("Adicionar veículo a partir da lista de mensalistas.");



        //buttons ActionsPerformed



        return view;

    }
}