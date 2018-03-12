package com.example.regis.medsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText phoneInput, passwordInput;
    RequestQueue requestQueue;
    SessionManage sessionManage;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneInput = (EditText) findViewById(R.id.phoneNoInput);
        passwordInput = (EditText) findViewById(R.id.PasswordInput);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        sessionManage = new SessionManage(getApplicationContext());


    }

    @Override
    public void onClick(View v) {
        requestQueue = VolleySingle.getInstance().getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://rex7.890m.com/login.php", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                String message = "";
                JSONObject jsonObject;

                try {
                    jsonObject = new JSONObject(response);
                    message = jsonObject.getString("Message");


                    if (message.equals("successful")) {

                        sessionManage.createSession(passwordInput.getText().toString(), phoneInput.getText().toString(), jsonObject.getString("user"));
                        startActivity(new Intent(getApplicationContext(), Profile.class));

                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sorry UserName is not registered" + message, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    Log.v("myLog", e.getMessage());
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
                data.put("phoneNo", phoneInput.getText().toString().trim());
                data.put("password", passwordInput.getText().toString().trim());


                return data;

            }
        };
        requestQueue.add(stringRequest);

    }
}



