package com.example.regis.medsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class VolleyExample extends AppCompatActivity {
    TextView myData;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_example);
        Toolbar tool = (Toolbar) findViewById(R.id.toolVolley);

        setSupportActionBar(tool);
        getSupportActionBar().setTitle("MyApp");
        myData = (TextView) findViewById(R.id.myData);
        requestQueue = VolleySingle.getInstance().getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.google.co.in", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myData.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(stringRequest);
    }
}
