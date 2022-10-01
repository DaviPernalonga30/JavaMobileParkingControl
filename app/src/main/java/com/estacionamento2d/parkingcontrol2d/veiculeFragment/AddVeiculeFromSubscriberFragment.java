package com.estacionamento2d.parkingcontrol2d.veiculeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.Subscriber;
import com.estacionamento2d.javasrc.VeiculeClass;
import com.estacionamento2d.parkingcontrol2d.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Locale;


public class AddVeiculeFromSubscriberFragment extends Fragment {
    public Spinner spinner_search;


    public AddVeiculeFromSubscriberFragment() {
        // Required empty public constructor
    }


    public Spinner SpinnerManagement(View view, java.util.ArrayList<Subscriber> subsList){
        spinner_search = (Spinner) view.findViewById(R.id.spinner_search);
        ArrayList<String> strList = new ArrayList<>();
        for(int i = 0; i < subsList.size(); i = i + 1){
            strList.add(subsList.get(i).getName());
        }


        spinner_search.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, strList));


        return spinner_search;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_veicule_from_subscriber, container, false);
        //Spinner spinner_search = (Spinner) view.findViewById(R.id.spinner_search);



        TextInputEditText text_field_search = (TextInputEditText) view.findViewById(R.id.text_field_search);
        TextInputEditText text_field_timein_from_mensalist = (TextInputEditText) view.findViewById(R.id.text_field_timein_from_mensalist);
        Button button_confirm_search = (Button) view.findViewById(R.id.button_confirm_search);
        Button button_cancel_add_from_mensalist = (Button) view.findViewById(R.id.button_cancel_add_from_mensalist);
        Button button_confrim_add_from_mensalist = (Button) view.findViewById(R.id.button_confrim_add_from_mensalist);

        button_confirm_search.setText("Buscar");
        button_cancel_add_from_mensalist.setText("Cancelar");
        button_confrim_add_from_mensalist.setText("Confirmar");
        DataBaseManagement db = new DataBaseManagement();
        java.util.ArrayList<VeiculeClass> veiculeList= db.selectFromVeicule();
        java.util.ArrayList<Subscriber> subscribers = db.selectFromSubscriber();

        spinner_search = SpinnerManagement(view, subscribers);



        button_confirm_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aux = String.valueOf(text_field_search.getText()).toLowerCase(Locale.ROOT);
                java.util.ArrayList auxList = new java.util.ArrayList<>();
                for(int i = 0; i<subscribers.size(); i = i+1){
                    if(subscribers.get(i).getName().toLowerCase(Locale.ROOT).contains(aux)){
                        auxList.add(subscribers.get(i));
                    }
                    else if(subscribers.get(i).getLicense().toLowerCase(Locale.ROOT).contains(aux)){
                        auxList.add(subscribers.get(i));
                    }

                }

                spinner_search = SpinnerManagement(AddVeiculeFromSubscriberFragment.super.getView(), auxList);


            }
        });

        button_confrim_add_from_mensalist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = String.valueOf(spinner_search.getSelectedItem());
                java.util.ArrayList<VeiculeClass> auxList = new ArrayList<>();
                VeiculeClass veic = new VeiculeClass();

                for(int i = 0; i<subscribers.size(); i = i +1){
                    if(subscribers.get(i).getName().toLowerCase(Locale.ROOT).equals(str.toLowerCase(Locale.ROOT))){
                        veic.setLicense(subscribers.get(i).getLicense());
                        veic.setManualIsSubscriber(true);
                        break;

                    }

                }

                if(!String.valueOf(text_field_timein_from_mensalist.getText()).equals("")){
                    veic.setManualTimeIn(String.valueOf(text_field_timein_from_mensalist.getText()));
                }
                else{
                    veic.setAutoTimeIn();
                }
                veic.setIsMotorBike(false);
                veic.setHasKey(false);
                veic.setHasPaidEarly(false);
                veic.setDate();

                db.insertIntoVeicule(veic);

                Navigation.findNavController(view).navigate(R.id.action_addVeiculeFromSubscriberFragment_to_homePageFragment);
            }
        });

        button_cancel_add_from_mensalist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_addVeiculeFromSubscriberFragment_to_homePageFragment);
            }
        });






        return view;
    }
}