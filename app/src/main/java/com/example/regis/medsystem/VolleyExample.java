package com.example.regis.medsystem;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    TextView titleText;
    EditText username, password, confirmPassword, emailAddress, phoneNo;
    RequestQueue requestQueue;
    SessionManage sessionManage;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_example);
        sessionManage = new SessionManage(getApplicationContext());

        titleText = (TextView) findViewById(R.id.title_volley);
        username = (EditText) findViewById(R.id.input);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        phoneNo = (EditText) findViewById(R.id.phoneNo);
        register = (Button) findViewById(R.id.register);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DancingScript-Regular.ttf");
        titleText.setTypeface(typeface);
        titleText.setText(getString(R.string.medsystem));


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        requestQueue = VolleySingle.getInstance().getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://rex7.890m.com/register.php", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                builder.setTitle("Response");
                builder.setMessage(response);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        username.setText("");
                        password.setText("");
                        confirmPassword.setText("");
                        emailAddress.setText("");
                        phoneNo.setText("");


                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
                data.put("user_name", username.getText().toString());
                data.put("passkey", password.getText().toString());
                data.put("emailId", emailAddress.getText().toString());
                data.put("phoneNo", phoneNo.getText().toString());

                return data;

            }
        };
                requestQueue.add(stringRequest);

            }
        });


    }
}
