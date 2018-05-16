package com.example.regis.medsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    TextView titleName, aboutMe, Art, Count;
    Button edit;
    SessionManage sessionManage;
    HashMap<String, String> userData;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManage = new SessionManage(getApplicationContext());
        sessionManage.checkLogin();
        userData = sessionManage.getUserDetail();
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbarProfile);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            toolbar.setTitle("Account");

        }
        aboutMe = (TextView) findViewById(R.id.aboutMe);
        titleName = (TextView) findViewById(R.id.title_name);
        Art = (TextView) findViewById(R.id.art);
        Count = (TextView) findViewById(R.id.count);
        titleName.setText(userData.get("username"));

        edit = (Button) findViewById(R.id.editor);

        edit.setOnClickListener(this);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/DancingScript-Regular.ttf");
        Art.setTypeface(typeface);
        Count.setTypeface(typeface);
        Count.setText(sessionManage.getUserDetail().get("noOfArticle"));
        aboutMe.setTypeface(typeface);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.editor:
                startActivity(new Intent(this, ArticleDemo.class));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile_logout:

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Logout?");
                alertDialog.setMessage("Do you want to logout ?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionManage.Logout();

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog1 = alertDialog.create();

                alertDialog1.show();


                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
