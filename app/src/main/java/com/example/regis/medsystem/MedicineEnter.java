package com.example.regis.medsystem;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MedicineEnter extends AppCompatActivity {
    EditText name, category, price;


    Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_enter);
        name = (EditText) findViewById(R.id.medicineName);
        category = (EditText) findViewById(R.id.medicineCategory);
        price = (EditText) findViewById(R.id.medicinePrice);
        insert = (Button) findViewById(R.id.insert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


}
