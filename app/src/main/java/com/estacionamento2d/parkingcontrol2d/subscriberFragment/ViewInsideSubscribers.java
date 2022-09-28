package com.estacionamento2d.parkingcontrol2d.subscriberFragment;

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

public class ViewInsideSubscribers extends Fragment {
    public ViewInsideSubscribers() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_view_inside_subscribers, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_view_inside_subscribers);
        Button button_view_inside_subscribers_return_home_page = (Button) view.findViewById(R.id.button_view_inside_subscribers_return_home_page);

        ArrayList<VeiculeClass> aux1 = new DataBaseManagement().selectFromVeicule();
        ArrayList<VeiculeClass> aux2 = new ArrayList<>();
        for(int i = 0; i<aux1.size(); i=i+1){
            if(aux1.get(i).getIsSubscriber() == true){
                aux2.add(aux1.get(i));
            }

        }


        recyclerView.setAdapter(new AdapterVeicule(aux2));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        button_view_inside_subscribers_return_home_page.setText("Voltar.");
        button_view_inside_subscribers_return_home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_viewInsideSubscribers_to_homePageFragment);
            }
        });



        return view;
    }
}