package com.example.regis.medsystem;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.regis.medsystem.medicine.MedicineActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.CompositePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView nav;
    RecyclerView recyclerView;
    TextView titleText;
    recycler_mainAdapter recycler_mainAdapter;
    SessionManage sessionManage;
    HashMap<String, String> userDetails;
    boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManage = new SessionManage(getApplicationContext());
        status = sessionManage.isLogedIn();


        setContentView(R.layout.activity_main);
        nav = (NavigationView) findViewById(R.id.nav);
        Menu menu = nav.getMenu();
        if (status) {
            MenuItem item = menu.findItem(R.id.mainMenu).getSubMenu().findItem(R.id.logout);
            MenuItem item2 = menu.findItem(R.id.mainMenu).getSubMenu().findItem(R.id.manage);

            item.setVisible(true);
            item2.setVisible(true);
        }
        View header = nav.getHeaderView(0);
        titleText = (TextView) header.findViewById(R.id.title_header);


        if (status) {
            Toast.makeText(getApplicationContext(), "logged In ", Toast.LENGTH_LONG).show();
            sessionManage.getUserDetail();
            userDetails = sessionManage.getUserDetail();
            titleText.setText(userDetails.get("username"));

        } else {
            Toast.makeText(getApplicationContext(), "Not logged In ", Toast.LENGTH_LONG).show();
            titleText.setText("Guest");
        }

        //permission for storage
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(getApplicationContext(), "Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                if (permissionDeniedResponse.isPermanentlyDenied()) {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.CoordinateLayout), "storage permission missing", BaseTransientBottomBar.LENGTH_SHORT);
                    snackbar.show();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        };
        PermissionListener snackbarListener = SnackbarOnDeniedPermissionListener.Builder
                .with(findViewById(R.id.CoordinateLayout), "Storage permission is needed ")
                .withOpenSettingsButton("Settings")
                .withDuration(Snackbar.LENGTH_LONG)
                .withCallback(new Snackbar.Callback() {


                    @Override
                    public void onShown(Snackbar sb) {

                    }

                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {

                    }
                }).build();
        PermissionListener composite = new CompositePermissionListener(snackbarListener, permissionListener);


        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(composite)
                .check();


        nav.setNavigationItemSelectedListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //DrawerLayout declaration
        recyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        recycler_mainAdapter = new recycler_mainAdapter(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycler_mainAdapter);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_navigation, R.string.close_navigation);


        drawerLayout.addDrawerListener(actionBarDrawerToggle);


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (status) {
            nav.getMenu().findItem(R.id.mainMenu).getSubMenu().findItem(R.id.logout).setVisible(true);
        }
        switch (item.getItemId()) {
            case R.id.Med:
                startActivity(new Intent(this, MedicineActivity.class));
                break;
            case R.id.medEnter:
                startActivity(new Intent(this, MedicineEnter.class));
                break;
            case R.id.AboustUs:
                startActivity(new Intent(this, AboutUs.class));
                break;

            case R.id.manage:
                if (status) {
                    startActivity(new Intent(this, Profile.class));
                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.CoordinateLayout), "You need to LogIn for that option", Snackbar.LENGTH_LONG)
                            .setAction("NotLogedIn", null);
                    snackbar.show();

                }
                break;
            case R.id.logout:
                if (status) {
                    sessionManage.Logout();

                } else {
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.CoordinateLayout), "You need to LogIn for that to work", Snackbar.LENGTH_LONG)
                            .setAction("NotLogedIn", null);
                    snackbar.show();
                }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.login:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.Register:
                startActivity(new Intent(this, VolleyExample.class));
                break;


        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }


}
