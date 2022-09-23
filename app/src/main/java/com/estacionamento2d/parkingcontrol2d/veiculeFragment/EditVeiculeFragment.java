package com.estacionamento2d.parkingcontrol2d.veiculeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.VeiculeClass;
import com.estacionamento2d.parkingcontrol2d.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class EditVeiculeFragment extends Fragment {
    public Spinner EditVeiculeSpinnerCreator(View view){
        Spinner edit_veicule_spinner = (Spinner) view.findViewById(R.id.edit_veicule_spinner);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> auxList = new ArrayList<>();
                ArrayList<VeiculeClass> veicList = new DataBaseManagement().selectFromVeicule();
                for(int i = 0; i < veicList.size(); i = i+1){
                    auxList.add(veicList.get(i).getLicense());
                }

                edit_veicule_spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, auxList));

            }
        });
        th.run();
        try
        {
            th.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        return edit_veicule_spinner;
    }

    public void updateAuxiliar(VeiculeClass veicOld, VeiculeClass veicNew){
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                new DataBaseManagement().updateItemFromVeicule(veicOld, veicNew);
            }
        });
        th.start();
        try
        {
            th.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }



    public EditVeiculeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_edit_veicule, container, false);
        TextInputEditText text_input_edit_license = (TextInputEditText) view.findViewById(R.id.text_input_edit_license);
        TextInputEditText text_input_edit_timein = (TextInputEditText) view.findViewById(R.id.text_input_edit_timein);
        TextInputEditText text_input_edit_timeout = (TextInputEditText) view.findViewById(R.id.text_input_edit_timeout);
        CheckBox checkbox_edit_haskey = (CheckBox) view.findViewById(R.id.checkbox_edit_haskey);
        CheckBox checkbox_edit_ismotorbike = (CheckBox) view.findViewById(R.id.checkbox_edit_ismotorbike);
        CheckBox checkbox_edit_ismensalist =  (CheckBox) view.findViewById(R.id.checkbox_edit_ismensalist);
        CheckBox checkbox_edit_haspaidearly = (CheckBox) view.findViewById(R.id.checkbox_edit_haspaidearly);
        Button button_edit_confirm = (Button) view.findViewById(R.id.button_edit_confirm);
        Button button_edit_cancel = (Button) view.findViewById(R.id.button_edit_cancel);

        Spinner edit_veicule_spinner = EditVeiculeSpinnerCreator(view);
        button_edit_confirm.setText("Confirmar.");
        button_edit_cancel.setText("Cancelar.");

        button_edit_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<VeiculeClass> veicList = new DataBaseManagement().selectFromVeicule();
                int i = 0;
                String str = String.valueOf(edit_veicule_spinner.getSelectedItem());
                while(!veicList.get(i).getLicense().equals(str)){
                    i = i +1;
                }
                VeiculeClass auxVeiculeOld = veicList.get(i);
                VeiculeClass auxVeiculeNew = veicList.get(i);
                if(!TextUtils.isEmpty(text_input_edit_license.getText())){
                    auxVeiculeNew.setLicense(String.valueOf(text_input_edit_license.getText()).toUpperCase());
                }
                if(!TextUtils.isEmpty(text_input_edit_timein.getText())){
                    auxVeiculeNew.setManualTimeIn(String.valueOf(text_input_edit_timein.getText()));
                }
                if(!TextUtils.isEmpty(text_input_edit_timeout.getText())){
                    auxVeiculeNew.setManualTimeOut(String.valueOf(text_input_edit_timeout.getText()));
                }
                if(checkbox_edit_ismensalist.isChecked()){
                    auxVeiculeNew.setManualIsSubscriber(true);
                }
                else{
                    auxVeiculeNew.setIsSubscriber(new DataBaseManagement().selectFromSubscriber());
                }
                if(checkbox_edit_haskey.isChecked()){
                    auxVeiculeNew.setHasKey(true);
                }
                else{
                    auxVeiculeNew.setHasKey(false);
                }
                if(checkbox_edit_ismotorbike.isChecked()){
                    auxVeiculeNew.setIsMotorBike(true);
                }
                else{
                    auxVeiculeNew.setIsMotorBike(false);
                }
                if(checkbox_edit_haspaidearly.isChecked()){
                    auxVeiculeNew.setHasPaidEarly(true);
                }
                else{
                    auxVeiculeNew.setHasPaidEarly(false);
                }

                updateAuxiliar(auxVeiculeOld, auxVeiculeNew);

                Navigation.findNavController(view).navigate(R.id.action_editVeiculeFragment_to_homePageFragment);



            }
        });

        button_edit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.action_editVeiculeFragment_to_homePageFragment);
            }
        });









        return view;
    }
}