package com.example.regis.medsystem;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AboutUs extends AppCompatActivity {
    Toolbar tool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        tool = (Toolbar) findViewById(R.id.toolBarAbout);
        setSupportActionBar(tool);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("MedApp");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("MedApp");
    }
}
