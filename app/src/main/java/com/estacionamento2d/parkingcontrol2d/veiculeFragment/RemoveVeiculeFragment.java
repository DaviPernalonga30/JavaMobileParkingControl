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
import android.widget.Spinner;

import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.VeiculeClass;
import com.estacionamento2d.parkingcontrol2d.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class RemoveVeiculeFragment extends Fragment {
    public RemoveVeiculeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public Spinner CreateSpinner(View view){
        Spinner remove_veicule_spinner = (Spinner) view.findViewById(R.id.remove_veicule_spinner);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                ArrayList<String> auxList = new ArrayList<>();
                ArrayList<VeiculeClass> veiculeList = new DataBaseManagement().selectFromVeicule();
                for(int i = 0; i <veiculeList.size(); i=i+1){
                    if(TextUtils.isEmpty(veiculeList.get(i).getTimeOut())){
                        auxList.add(veiculeList.get(i).getLicense());
                    }

                }
                remove_veicule_spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, auxList));


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

        return remove_veicule_spinner;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_remove_veicule, container, false);

        Button remove_veicule_confirm = (Button) view.findViewById(R.id.remove_veicule_confirm);
        remove_veicule_confirm.setText("Confirmar.");
        Button remove_veicule_cancel = (Button) view.findViewById(R.id.remove_veicule_cancel);
        remove_veicule_cancel.setText("Cancelar.");


        TextInputEditText text_input_remove_veicule_timeout = (TextInputEditText) view.findViewById(R.id.text_input_remove_veicule_timeout);
        Spinner remove_veicule_spinner = this.CreateSpinner(view);


        remove_veicule_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String auxS = (String) remove_veicule_spinner.getSelectedItem();
                int auxI = remove_veicule_spinner.getSelectedItemPosition();
                int i = 0;
                java.util.ArrayList<VeiculeClass> veiculeList= new DataBaseManagement().selectFromVeicule();
                while(!auxS.equals(veiculeList.get(i).getLicense())){
                    i = i +1;
                }
                VeiculeClass auxliarVeic = veiculeList.get(i);
                if(TextUtils.isEmpty(text_input_remove_veicule_timeout.getText())){
                    veiculeList.get(i).setAutoTimeOut();
                }
                else{
                    veiculeList.get(i).setManualTimeOut(String.valueOf(text_input_remove_veicule_timeout.getText()));
                }

                updateAuxiliar(auxliarVeic, veiculeList.get(i));


                Navigation.findNavController(view).navigate(R.id.action_removeVeiculeFragment_to_homePageFragment);


            }
        });

        remove_veicule_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_removeVeiculeFragment_to_homePageFragment);

            }
        });




        return view;
    }
}