package com.estacionamento2d.parkingcontrol2d.subscriberFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estacionamento2d.adapter.AdapterSubscriber;
import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.Subscriber;
import com.estacionamento2d.parkingcontrol2d.R;


public class SubscriberRecyclerViewFragment extends Fragment {
    public SubscriberRecyclerViewFragment() {
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

        //Cria a RecyclerView e coloca ela no fragment.
        View view = inflater.inflate(R.layout.fragment_subscriber_recycler_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_subscriber);
        DataBaseManagement db = new DataBaseManagement();
        java.util.ArrayList<Subscriber> subList= db.selectFromSubscriber();
        recyclerView.setAdapter(new AdapterSubscriber(subList));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}