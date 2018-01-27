package com.example.regis.medsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Medicine extends AppCompatActivity {
Toolbar toolbar;
    RecyclerView recyclerView;
    List<String> medName=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Medicine");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addData();
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(medName,this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public void addData(){
        medName.add("Paracetamol 500mg Tab ");
        medName.add("Aceclofenac ");
        medName.add("PPentazocine 30 mg ");
        medName.add("Nimesulide 100 mg Tab  ");
        medName.add("Indomethacin 25 mg Cap");
        medName.add("Ibuprofen film coated Tablets IP 200mg");
        medName.add("Ibuprofen 400mg + Paracetamol 325 mg Tab");
        medName.add("Etoricoxilb 120mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");



    }
}
