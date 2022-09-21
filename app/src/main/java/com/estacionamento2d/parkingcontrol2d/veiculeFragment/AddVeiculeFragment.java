package com.estacionamento2d.parkingcontrol2d.veiculeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.VeiculeClass;
import com.estacionamento2d.parkingcontrol2d.R;
import com.google.android.material.textfield.TextInputEditText;


public class AddVeiculeFragment extends Fragment {
    public AddVeiculeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_veicule, container, false);


        TextInputEditText text_input_add_veicule_license = (TextInputEditText) view.findViewById(R.id.text_input_add_veicule_license);
        TextInputEditText text_input_add_veicule_timein = (TextInputEditText) view.findViewById(R.id.text_input_add_veicule_timein);
        CheckBox add_veicule_haskey = (CheckBox) view.findViewById(R.id.add_veicule_haskey);
        CheckBox add_veicule_ismotorbike = (CheckBox) view.findViewById(R.id.add_veicule_ismotorbike);
        Button add_veicule_confirm = (Button) view.findViewById(R.id.add_veicule_confirm);
        Button add_veicule_cancel = (Button) view.findViewById(R.id.add_veicule_cancel);

        add_veicule_confirm.setText("Confirmar.");
        add_veicule_cancel.setText("Cancelar.");


        add_veicule_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeiculeClass veicule = new VeiculeClass();
                veicule.setDate();
                veicule.setLicense(text_input_add_veicule_license.getText().toString());
                if(TextUtils.isEmpty(text_input_add_veicule_timein.getText())){
                    veicule.setAutoTimeIn();
                }
                else{
                    veicule.setManualTimeIn(text_input_add_veicule_timein.getText().toString());
                }
                if(add_veicule_haskey.isChecked() == true){
                    veicule.setHasKey(true);
                }
                else{
                    veicule.setHasKey(false);
                }
                if(add_veicule_ismotorbike.isChecked() == true){
                    veicule.setIsMotorBike(true);
                }
                else{
                    veicule.setIsMotorBike(false);
                }


                veicule.setIsSubscriber(new DataBaseManagement().selectFromSubscriber());
                new DataBaseManagement().insertIntoVeicule(veicule);


                Navigation.findNavController(view).navigate(R.id.action_addVeiculeFragment_to_homePageFragment);



            }
        });



        add_veicule_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_addVeiculeFragment_to_homePageFragment);
            }
        });




        return view;
    }
}