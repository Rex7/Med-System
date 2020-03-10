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
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.regis.medsystem.R;
import com.example.regis.medsystem.VolleySingle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MedicineActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    List<AppModel> appModels=new ArrayList<>();

    CollapsingToolbarLayout collapsingToolbarLayout;
    ProgressBar progressBar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        progressBar =  findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycle);
        collapsingToolbarLayout =  findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("Medicine");
        toolbar =  findViewById(R.id.toolBar);
        context = this;


        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("App Store");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //setting a empty adpater

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        RequestQueue myRequestQueue = VolleySingle.getInstance().getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://rexmyapp.000webhostapp.com/getAllData.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                       /* medicineDataList.add(new MedicineData(obj.getString("drugName"), obj.getString("category"), obj.getString("Usage")
                                , obj.getString("SideEffect"), obj.getInt("price"), obj.getInt("drugId")));*/
                        appModels.add(new AppModel(obj.getString("appName"),obj.getString("appSize")));


                    }
                    Collections.sort(appModels, new Comparator<AppModel>() {
                        @Override
                        public int compare(AppModel o1, AppModel o2) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    });

                    recyclerAdapter = new RecyclerAdapter(appModels, MedicineActivity.this);
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
        })
        {
        @Override
        protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
            Log.v("MedSystem","Loading from cache");
            try {
                Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                if (cacheEntry == null) {
                    cacheEntry = new Cache.Entry();
                }
                final long cacheHitButRefreshed = 3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                long now = System.currentTimeMillis();
                final long softExpire = now + cacheHitButRefreshed;
                final long ttl = now + cacheExpired;
                cacheEntry.data = response.data;
                cacheEntry.softTtl = softExpire;
                cacheEntry.ttl = ttl;
                String headerValue;
                headerValue = response.headers.get("Date");
                if (headerValue != null) {
                    cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                }
                headerValue = response.headers.get("Last-Modified");
                if (headerValue != null) {
                    cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                }
                cacheEntry.responseHeaders = response.headers;
                final String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONArray(jsonString), cacheEntry);
            } catch (UnsupportedEncodingException | JSONException e) {
                return Response.error(new ParseError(e));
            }
        }

        @Override
        protected void deliverResponse(JSONArray response) {
            super.deliverResponse(response);
        }

        @Override
        public void deliverError(VolleyError error) {
            super.deliverError(error);
        }

        @Override
        protected VolleyError parseNetworkError(VolleyError volleyError) {
            return super.parseNetworkError(volleyError);
        }
    };


        myRequestQueue.add(jsonArrayRequest);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //this function is just called not used anymore

}
