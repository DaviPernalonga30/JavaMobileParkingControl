package com.estacionamento2d.parkingcontrol2d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estacionamento2d.adapter.AdapterSubscriber;
import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.Subscriber;


import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseManagement db = new DataBaseManagement();


        setContentView(R.layout.activity_main);
        java.util.ArrayList<Subscriber> a = new ArrayList();
        a = db.selectFromSubscriber();

        String aux1 = a.get(0).getName();
        String aux2 = a.get(0).getLicense();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.MainRecylerView);
        recyclerView.setAdapter(new AdapterSubscriber(a));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        





        System.out.println(a);
    }
}