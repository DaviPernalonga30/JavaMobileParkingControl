package com.estacionamento2d.parkingcontrol2d.veiculeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estacionamento2d.adapter.AdapterVeicule;
import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.VeiculeClass;
import com.estacionamento2d.parkingcontrol2d.R;

public class VeiculeRecyclerViewFragment extends Fragment {

    public VeiculeRecyclerViewFragment() {
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
        View view = inflater.inflate(R.layout.fragment_veicule_recycler_view, container, false);
        DataBaseManagement db = new DataBaseManagement();
        java.util.ArrayList<VeiculeClass> veicList = db.selectFromVeicule();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_veicule);
        recyclerView.setAdapter(new AdapterVeicule(veicList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }
}