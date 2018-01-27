package com.example.regis.medsystem;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolders> {
    private List<String> medNames;
    private Context mContext;

    public RecyclerAdapter(List<String> medNames,Context mContext) {
        this.medNames = medNames;
        this.mContext=mContext;
    }

    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
       View v=  LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewHolders(v);
    }

    @Override
    public void onBindViewHolder(ViewHolders holder, final int position) {
        holder.medName.setText(medNames.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext," "+medNames.get(position),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return medNames.size();
    }

    public static  class ViewHolders extends RecyclerView.ViewHolder{
TextView medName;

        public ViewHolders(View itemView) {
            super(itemView);

            medName=(TextView)itemView.findViewById(R.id.medicine_name);
        }
    }

}

