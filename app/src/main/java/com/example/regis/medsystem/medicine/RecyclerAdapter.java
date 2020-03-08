package com.example.regis.medsystem.medicine;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.regis.medsystem.MyBottomSheet;
import com.example.regis.medsystem.R;

import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolders> {
    private List<AppModel> appModels;
    private Context mContext;
    private RelativeLayout relativeLayout;
    private BottomSheetBehavior bottomSheetBehavior;

    public RecyclerAdapter(List<AppModel> appModels, Context mContext) {
        this.appModels = appModels;
        this.mContext=mContext;
    }

    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
       View v=  LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolders(v, mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, final int position) {
        holder.game.setText(appModels.get(position).getName());
        holder.data.setText(appModels.get(position).getSize());

    }

    @Override
    public int getItemCount() {

        return appModels.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
TextView game,data;
        Context ct;

        public ViewHolders(View itemView, Context ct) {
            super(itemView);
            this.ct = ct;
            game=(TextView)itemView.findViewById(R.id.gameName);
            data=(TextView)itemView.findViewById(R.id.data);


            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            MyBottomSheet bottomSheet = new MyBottomSheet();
            MedicineActivity medicineActivity = (MedicineActivity) mContext;
            bottomSheet.setMedicine(appModels.get(pos));

            bottomSheet.show(medicineActivity.getSupportFragmentManager(), bottomSheet.getTag());
            Toast.makeText(ct, " " + appModels.get(pos).getName() + "\n Price " + appModels.get(pos).getSize(), Toast.LENGTH_LONG).show();


        }
    }

}

