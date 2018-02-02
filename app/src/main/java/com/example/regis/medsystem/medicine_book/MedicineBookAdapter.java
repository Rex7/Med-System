package com.example.regis.medsystem.medicine_book;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.regis.medsystem.R;


public class MedicineBookAdapter extends RecyclerView.Adapter<MedicineBookAdapter.ViewHolder> {
    Context context;

    public MedicineBookAdapter(Context context) {
        this.context = context;
    }


    private String[] title = {"Essentials of Medical Pharmacology", "Clinical Methods", "Principles and practice of medicine", "Fuctional Histology"};
    private String[] authorName = {" KD Tripathi,", "Robert", "DavidSon", "Wheater's"};
    private int[] bookCover = {R.drawable.medbookcover, R.drawable.cover, R.drawable.mymedcover, R.drawable.medimage};


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medbook, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bookTitle.setText(title[position]);
        holder.authorName.setText(authorName[position]);
        Glide.with(context)
                .load(bookCover[position])
                .into(holder.bookCover);

    }

    @Override
    public int getItemCount() {
        return authorName.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, authorName;
        ImageView bookCover;


        public ViewHolder(View view) {
            super(view);
            bookTitle = (TextView) view.findViewById(R.id.title_book);
            authorName = (TextView) view.findViewById(R.id.author_name);
            bookCover = (ImageView) view.findViewById(R.id.book_cover);

        }
    }
}
