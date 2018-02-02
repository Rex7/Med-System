package com.example.regis.medsystem.medicine_book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.regis.medsystem.R;

public class MedicalBook extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_book);
        toolbar = (Toolbar) findViewById(R.id.toolbarMedBook);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.medBook);
        MedicineBookAdapter medicineBookAdapter = new MedicineBookAdapter(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(medicineBookAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }
}
