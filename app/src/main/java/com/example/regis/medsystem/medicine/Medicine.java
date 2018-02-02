package com.example.regis.medsystem.medicine;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.regis.medsystem.Drug;
import com.example.regis.medsystem.R;

import java.util.ArrayList;
import java.util.List;

public class Medicine extends AppCompatActivity {
Toolbar toolbar;
    RecyclerView recyclerView;
    List<Drug> medName = new ArrayList<>();
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("Medicine M");
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
        medName.add(new Drug("Paracetamol 500mg Tab "));
        medName.add(new Drug("Aceclofenac "));
        medName.add(new Drug("PPentazocine 30 mg "));
        medName.add(new Drug("Nimesulide 100 mg Tab  "));
        medName.add(new Drug("Indomethacin 25 mg Cap"));
        medName.add(new Drug("Ibuprofen film coated Tablets IP 200mg"));
        medName.add(new Drug("Ibuprofen 400mg + Paracetamol 325 mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));
        medName.add(new Drug("Etoricoxilb 120mg Tab"));





    }
}
