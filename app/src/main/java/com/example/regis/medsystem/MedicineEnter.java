package com.example.regis.medsystem;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.regis.medsystem.database.Medicine;
import com.example.regis.medsystem.database.MedicineDatabase;

import java.util.List;

public class MedicineEnter extends AppCompatActivity {
    EditText name, category, price;
    MedicineDatabase medicineDatabase;

    Button insert, showAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_enter);
        name = (EditText) findViewById(R.id.medicineName);
        category = (EditText) findViewById(R.id.medicineCategory);
        price = (EditText) findViewById(R.id.medicinePrice);
        insert = (Button) findViewById(R.id.insert);
        showAll = (Button) findViewById(R.id.showAll);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medicine medicine = new Medicine();
                medicine.setMedName(name.getText().toString());
                medicine.setCategory(category.getText().toString());
                medicine.setPrice((Integer.parseInt(price.getText().toString())));
                medicineDatabase = MedicineDatabase.getInstance(getApplicationContext());
                MyAsync myAsync = new MyAsync(medicineDatabase, 1);
                myAsync.execute(medicine);

            }
        });
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineDatabase = MedicineDatabase.getInstance(getApplicationContext());
                MyAsync myAsync = new MyAsync(medicineDatabase, 2);
                myAsync.execute();
            }
        });


    }

    private class MyAsync extends AsyncTask<Medicine, Void, Void> {
        private MedicineDatabase db;
        private int code;

        private MyAsync(MedicineDatabase db, int code) {
            this.db = db;
            this.code = code;
        }

        @Override
        protected Void doInBackground(Medicine... params) {
            switch (code) {
                case 1:
                    db.medicineDoa().insertMedicine(params[0]);
                    break;
                case 2:
                    List<Medicine> medicineList = db.medicineDoa().getAll();
                    Log.v("Med", "data " + medicineList);
            }


            return null;
        }
    }
}
