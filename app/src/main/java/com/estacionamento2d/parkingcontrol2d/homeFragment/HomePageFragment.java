package com.estacionamento2d.parkingcontrol2d.homeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.estacionamento2d.javasrc.Calculations;
import com.estacionamento2d.javasrc.DataBaseManagement;
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
        TextView textViewTotalVeicule = (TextView) view.findViewById(R.id.textView_total_veicule);
        TextView textViewTotalSubs = (TextView) view.findViewById(R.id.textView_total_subs);
        TextView textViewDailyMoney = (TextView) view.findViewById(R.id.textView_daily_money);

        Calculations calcs = new Calculations();
        calcs.setValTurnCar(7);
        calcs.setValTurnMoto(3);

        //TextView setText
        textViewTotalVeicule.setText("O total de veículos no estacionamento é: "+ String.valueOf(new DataBaseManagement().selectFromVeicule().size()));
        textViewTotalSubs.setText("O total de mensalistas do estacionemento é: "+ String.valueOf(new DataBaseManagement().selectFromSubscriber().size()));
        textViewDailyMoney.setText("O Valor recebido no dia é: "+ String.valueOf(calcs.getDayReturn(new DataBaseManagement().selectFromVeicule())));


        //Buttons declarations
        Button buttonAddVeiculeFromSubscriber = (Button) view.findViewById(R.id.button_add_veicule_from_subscriber);
        Button buttonAddVeicule = (Button) view.findViewById(R.id.button_add_veicule);
        Button buttonEditVeicule = (Button) view.findViewById(R.id.button_edit_veicule);
        Button buttonRemoveVeicule = (Button) view.findViewById(R.id.button_remove_veicule);



        //buttons setText
        buttonAddVeiculeFromSubscriber.setText("Adicionar veículo a partir da lista de mensalistas.");
        buttonAddVeicule.setText("Adicionar veículo.");
        buttonEditVeicule.setText("Editar Veículo.");
        buttonRemoveVeicule.setText("Remover Veículo.");



        //buttons ActionsPerformed
        buttonAddVeiculeFromSubscriber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_homePageFragment_to_addVeiculeFromSubscriberFragment);

            }
        });


        buttonAddVeicule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_homePageFragment_to_addVeiculeFragment);

            }
        });


        buttonEditVeicule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_homePageFragment_to_editVeiculeFragment);

            }
        });


        buttonRemoveVeicule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_homePageFragment_to_removeVeiculeFragment);

            }
        });



        return view;

    }
}