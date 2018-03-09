package com.example.regis.medsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class MyViewPdf extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pdf);
        pdfView = (PDFView) findViewById(R.id.myPdfView);
        pdfView.fromAsset("tripathi.pdf").load();


    }
}
