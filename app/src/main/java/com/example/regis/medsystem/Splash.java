package com.example.regis.medsystem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    ViewPager viewPager;
    int count = 0;
    ViewPagerAdapter viewPagerAdapter;
    LinearLayout dots_layout;
    TextView dots[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext());
        viewPager.setAdapter(viewPagerAdapter);
        dots_layout = (LinearLayout) findViewById(R.id.dots);
        addThreeDots(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addThreeDots(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void startMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void addThreeDots(int pos) {
        dots = new TextView[3];
        dots_layout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextColor(getResources().getColor(R.color.Transparen));
            dots[i].setTextSize(38);
            dots_layout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[pos].setTextColor(Color.BLACK);
        }


    }

}
