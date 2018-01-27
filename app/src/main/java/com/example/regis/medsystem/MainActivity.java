package com.example.regis.medsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
        RecyclerView recyclerView;
    NavigationView nav;
    List<String> medName=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_navigation, R.string.close_navigation);
        addData();
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(medName,this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        drawerLayout.addDrawerListener(actionBarDrawerToggle);


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void addData(){
        medName.add("Paracetamol 500mg Tab ");
        medName.add("Aceclofenac ");
        medName.add("PPentazocine 30 mg ");
        medName.add("Nimesulide 100 mg Tab  ");
        medName.add("Indomethacin 25 mg Cap");
        medName.add("Ibuprofen film coated Tablets IP 200mg");
        medName.add("Ibuprofen 400mg + Paracetamol 325 mg Tab");
        medName.add("Etoricoxilb 120mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");
        medName.add("Etoricoxilb 90mg Tab");



    }
}
