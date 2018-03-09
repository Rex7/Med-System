package com.example.regis.medsystem;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {
    Toolbar tool;
    TextView firstPara, secondPar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        firstPara = (TextView) findViewById(R.id.myHeading);
        secondPar = (TextView) findViewById(R.id.myAim);
        Typeface heading = Typeface.createFromAsset(getAssets(), "fonts/CrimsonText-Regular.ttf");
        firstPara.setTypeface(heading);
        secondPar.setTypeface(heading);
        firstPara.setText("This application focuses on understanding the basic of medicine" +
                "and how to use them ");
        secondPar.setText("This Application is crowdsourcin based where every individual provides us with information and current " +
                "advancement in Medicine ");




        tool = (Toolbar) findViewById(R.id.toolBarAbout);
        setSupportActionBar(tool);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("AboutUs");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);
        collapsingToolbarLayout.setTitle("AboutUs");
    }
}
