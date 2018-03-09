package com.example.regis.medsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://rex7.890m.com/register.php", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                myData.setText(response);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> data = new HashMap<>();
                data.put("user_name", "jenifer");
                data.put("passkey", "loveyou");
                data.put("emailId", "jeniferbhaskar@gmail.com");
                data.put("phoneNo", "802354789");

                return data;

            }
        };


        requestQueue.add(stringRequest);
    }
}
