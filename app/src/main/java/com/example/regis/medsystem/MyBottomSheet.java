package com.example.regis.medsystem;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.TextView;

import com.example.regis.medsystem.medicine.AppModel;
import com.example.regis.medsystem.medicine.MedicineData;


public class MyBottomSheet extends BottomSheetDialogFragment {
    AppModel appModel;
    TextView Name, price;

    public MyBottomSheet() {
        super();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View v = View.inflate(getContext(), R.layout.bottomsheet, null);
        Name = (TextView) v.findViewById(R.id.Name);
        price = (TextView) v.findViewById(R.id.botton_price_name);

        Name.setText(appModel.getName());
        price.setText("" + appModel.getName());


        dialog.setContentView(v);
    }

    public void setMedicine(AppModel appModel) {
        this.appModel = appModel;
    }
}
