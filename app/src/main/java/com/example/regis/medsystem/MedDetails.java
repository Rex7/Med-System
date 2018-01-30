package com.example.regis.medsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MedDetails extends AppCompatActivity {
    TextView title_Text;
    Toolbar toolbar;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar_medDetails);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            data = (String) bundle.get("drug_name");

        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        title_Text = (TextView) findViewById(R.id.title_text);
        title_Text.setText(data);

    }
}
