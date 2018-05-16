package com.example.regis.medsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ArticleDemo extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    RequestQueue requestQueue;
    EditText articleTitle, articleAuthor, articleContent;
    Button submitArticle;
    SessionManage sessionManage;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManage = new SessionManage(getApplicationContext());

        id = sessionManage.getUserDetail().get("phoneNo");

        setContentView(R.layout.activity_article_demo);
        articleTitle = (EditText) findViewById(R.id.articleTitle);
        articleAuthor = (EditText) findViewById(R.id.articleAuthorName);
        articleContent = (EditText) findViewById(R.id.articleAuthorContent);
        submitArticle = (Button) findViewById(R.id.submitArticle);
        toolbar = (Toolbar) findViewById(R.id.toolbarArticle);
        setSupportActionBar(toolbar);
        requestQueue = VolleySingle.getInstance().getRequestQueue();

        submitArticle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://rex7.890m.com/insertArticle.php", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                String message;
                JSONObject jsonObject;

                try {
                    jsonObject = new JSONObject(response);
                    message = jsonObject.getString("Message");


                    if (message.equals("Article Submitted")) {
                        Toast.makeText(getApplicationContext(), "Article submitted", Toast.LENGTH_LONG).show();
                        articleAuthor.setText("");
                        articleContent.setText("");
                        articleTitle.setText("");


                    } else {
                        Toast.makeText(getApplicationContext(), "Sorry due to some error" + message, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.i("myLog", e.getMessage());
                }


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
                data.put("articleId", id);
                data.put("title", articleTitle.getText().toString());
                data.put("paragraph", articleContent.getText().toString());
                data.put("authorName", articleAuthor.getText().toString());


                return data;

            }
        };
        requestQueue.add(stringRequest);


    }


}

