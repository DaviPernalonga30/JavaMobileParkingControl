package com.estacionamento2d.parkingcontrol2d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.estacionamento2d.adapter.AdapterSubscriber;
import com.estacionamento2d.adapter.AdapterVeicule;
import com.estacionamento2d.javasrc.DataBaseManagement;
import com.estacionamento2d.javasrc.Subscriber;
import com.estacionamento2d.javasrc.VeiculeClass;


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

        setContentView(R.layout.activity_main);

        //button declarations.
        Button button_viewVeicules = (Button) findViewById(R.id.button_viewVeicules);


        //button setTexts.
        button_viewVeicules.setText("Ver Veículos.");
        button_viewVeicules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }
}