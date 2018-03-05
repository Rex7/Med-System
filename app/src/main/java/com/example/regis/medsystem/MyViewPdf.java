package com.example.regis.medsystem;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.io.InputStream;

public class MyViewPdf extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pdf);
        pdfView = (PDFView) findViewById(R.id.myPdfView);
        AssetManager assetManager = getResources().getAssets();
        try {
            InputStream inputStream = assetManager.open("mydata.pdf");
            pdfView.fromAsset("mydata.pdf").load();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
