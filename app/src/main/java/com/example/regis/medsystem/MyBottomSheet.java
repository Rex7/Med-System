package com.example.regis.medsystem;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.TextView;

import com.example.regis.medsystem.medicine.MedicineData;


public class MyBottomSheet extends BottomSheetDialogFragment {
    MedicineData medicine;
    TextView Name, price, usage, sideEffect;

    public MyBottomSheet() {
        super();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View v = View.inflate(getContext(), R.layout.bottomsheet, null);
        Name = (TextView) v.findViewById(R.id.Name);
        price = (TextView) v.findViewById(R.id.botton_price_name);
        usage = (TextView) v.findViewById(R.id.botton_USage_text);
        sideEffect = (TextView) v.findViewById(R.id.sideEffectsText);
        Name.setText(medicine.getDrugName());
        price.setText("" + medicine.getPrice());
        usage.setText(medicine.getUsage());
        sideEffect.setText(medicine.getSideEffects());


        dialog.setContentView(v);
    }

    public void setMedicine(MedicineData medicine) {
        this.medicine = medicine;
    }
}
