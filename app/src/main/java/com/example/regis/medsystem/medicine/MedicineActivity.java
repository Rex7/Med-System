package com.example.regis.medsystem.medicine;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.regis.medsystem.Drug;
import com.example.regis.medsystem.R;
import com.example.regis.medsystem.VolleySingle;
import com.example.regis.medsystem.database.Medicine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<Drug> medName = new ArrayList<>();
    List<Medicine> med = new ArrayList<>();
    List<MedicineData> medicineDataList = new ArrayList<>();
    List<Medicine> medList = new ArrayList<>();
    CollapsingToolbarLayout collapsingToolbarLayout;
    ProgressBar progressBar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
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

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        RequestQueue myRequestQueue = VolleySingle.getInstance().getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "http://rex7.890m.com/getData.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        medList.add(new Medicine(obj.getInt("drugId"), obj.getString("drugName"), obj.getString("category"), obj.getInt("price")));
                        medicineDataList.add(new MedicineData(obj.getString("drugName"), obj.getString("category"), obj.getString("Usage")
                                , obj.getString("SideEffect"), obj.getInt("price"), obj.getInt("drugId")));
                    }
                    Collections.sort(medicineDataList, new Comparator<MedicineData>() {
                        @Override
                        public int compare(MedicineData o1, MedicineData o2) {
                            return o2.getDrugName().compareTo(o1.getDrugName());
                        }
                    });

                    recyclerAdapter = new RecyclerAdapter(medicineDataList, MedicineActivity.this);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        myRequestQueue.add(jsonArrayRequest);
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
