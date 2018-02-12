package com.example.regis.medsystem.medicine;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.example.regis.medsystem.Drug;
import com.example.regis.medsystem.R;
import com.example.regis.medsystem.database.Medicine;
import com.example.regis.medsystem.database.MedicineDatabase;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Drug> medName = new ArrayList<>();
    List<Medicine> med = new ArrayList<>();
    CollapsingToolbarLayout collapsingToolbarLayout;
    RelativeLayout relativeLayout;
    BottomSheetBehavior bottomSheetBehavior;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("Medicine");
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        context = this;


        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Medicine");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //setting a empty adpater
        recyclerAdapter = new RecyclerAdapter(med, this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        //calling datbase to add data
        MedicineDatabase database = MedicineDatabase.getInstance(this);
        MyAsync myAsync = new MyAsync(database);
        myAsync.execute();






    }

    private class MyAsync extends AsyncTask<Object, Object, List<Medicine>> {
        List<Medicine> medicines;

        @Override
        protected void onPostExecute(List<Medicine> med) {


            recyclerView.setAdapter(new RecyclerAdapter(med, context));
            recyclerAdapter.notifyDataSetChanged();


        }

        MedicineDatabase db;

        MyAsync(MedicineDatabase db) {
            this.db = db;

        }

        @Override
        protected List<Medicine> doInBackground(Object... params) {

            medicines = db.medicineDoa().getAll();

            return medicines;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //this function is just called not used anymore
    public void addData() {
        medName.add(new Drug("Paracetamol 500mg Tab "));

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


        Medicine medicine = new Medicine();
        medicine.setMedName("Paracetamol 500mg Tab");
        medicine.setCategory("crocin");
        medicine.setPrice(15);
        med.add(medicine);
        Medicine medicine2 = new Medicine();
        medicine.setMedName("Paracetamol 500mg Tab");
        medicine.setCategory("crocin");
        medicine.setPrice(15);
        med.add(medicine);



    }
}
