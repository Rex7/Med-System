package com.example.regis.medsystem.medicine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.regis.medsystem.MedDetails;
import com.example.regis.medsystem.R;
import com.example.regis.medsystem.database.Medicine;

import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolders> {
    private List<Medicine> medNames;
    private Context mContext;

    public RecyclerAdapter(List<Medicine> medNames, Context mContext) {
        this.medNames = medNames;
        this.mContext=mContext;
    }

    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
       View v=  LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolders(v, mContext);
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, final int position) {
        holder.medName.setText(medNames.get(position).getMedName());

    }

    @Override
    public int getItemCount() {
        return medNames.size();
    }

    public class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
TextView medName;
        Context ct;

        public ViewHolders(View itemView, Context ct) {
            super(itemView);
            this.ct = ct;
            medName=(TextView)itemView.findViewById(R.id.medicine_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();

            Toast.makeText(mContext, " " + medNames.get(pos), Toast.LENGTH_LONG).show();


            Intent intent = new Intent(ct, MedDetails.class);
            intent.putExtra("drug_name", medNames.get(pos).getMedName());
            this.ct.startActivity(intent);
        }
    }

}

