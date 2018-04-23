package com.example.regis.medsystem.research;

import android.content.Context;
import android.os.Bundle;
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
import com.example.regis.medsystem.R;
import com.example.regis.medsystem.VolleySingle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ResearchAndDevelop extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ResearchAdapter researchAdapter;
    ProgressBar progressBar;
    Context context;
    List<ArticleClass> artcileList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_and_develop);
        progressBar = (ProgressBar) findViewById(R.id.progressBarArticle);
        recyclerView = (RecyclerView) findViewById(R.id.myrecycleResearch);
        toolbar = (Toolbar) findViewById(R.id.toolbarArticle);
        context = this;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Research&Develop");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //setting a empty adpater

        recyclerView.setAdapter(researchAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        RequestQueue myRequestQueue = VolleySingle.getInstance().getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "http://rex7.890m.com/getArticle.php", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject obj = response.getJSONObject(i);
                        artcileList.add(new ArticleClass(obj.getString("title"), obj.getString("authorName"), obj.getString("paragraph")));

                    }


                    researchAdapter = new ResearchAdapter(ResearchAndDevelop.this, artcileList);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(researchAdapter);
                    researchAdapter.notifyDataSetChanged();


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


}

