package com.example.regis.medsystem.medicine_book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.regis.medsystem.R;


public class MedicineBookAdapter extends RecyclerView.Adapter<MedicineBookAdapter.ViewHolder> {
    private Context context;

    public MedicineBookAdapter(Context context) {
        this.context = context;
    }


    private String[] title = {"Essentials of Medical Pharmacology", "Clinical Methods", "Principles and practice of medicine", "Fuctional Histology", "live life"};
    private String[] authorName = {" KD Tripathi,", "Robert", "DavidSon", "Wheater's", "Regis"};
    private int[] bookCover = {R.drawable.medbookcover, R.drawable.medbookcover, R.drawable.mymedcover, R.drawable.medimage, R.drawable.medimage};


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medbook, parent, false);
        return new ViewHolder(context, v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bookTitle.setText(title[position]);
        holder.authorName.setText(authorName[position]);
        Glide.with(context)
                .load(bookCover[position])
                .into(holder.bookCover);
        holder.bookCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Opening book please wait...", Toast.LENGTH_LONG).show();
            }
        });
        holder.ic_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(context, holder.ic_dot);
            }
        });
    }

    @Override
    public int getItemCount() {
        return authorName.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        Context myContext;
        TextView bookTitle, authorName;
        ImageView bookCover, ic_dot;
        View myView;


        public ViewHolder(Context myContext
                , final View view) {
            super(view);
            myView = view;
            this.myContext = myContext;

            bookTitle = (TextView) view.findViewById(R.id.title_book);
            authorName = (TextView) view.findViewById(R.id.author_name);
            bookCover = (ImageView) view.findViewById(R.id.book_cover);
            ic_dot = (ImageView) view.findViewById(R.id.myDot);


        }

    }

    public void showPopUp(final Context con, View v) {
        PopupMenu popupMenu = new PopupMenu(con, v);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.med_book_menu, popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addShare:
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "This is a simple share");
                        intent.setType("text/plain");
                        con.startActivity(intent);
                        break;
                    case R.id.download:
                        Toast.makeText(con, "Share", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

    }
}
