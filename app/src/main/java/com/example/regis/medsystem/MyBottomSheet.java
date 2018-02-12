package com.example.regis.medsystem;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.TextView;

import com.example.regis.medsystem.database.Medicine;


public class MyBottomSheet extends BottomSheetDialogFragment {
    Medicine medicine;
    TextView Name, price;

    public MyBottomSheet() {
        super();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View v = View.inflate(getContext(), R.layout.bottomsheet, null);
        Name = (TextView) v.findViewById(R.id.Name);
        price = (TextView) v.findViewById(R.id.botton_price_name);
        Name.setText(medicine.getMedName());
        price.setText("" + medicine.getPrice());


        dialog.setContentView(v);
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
