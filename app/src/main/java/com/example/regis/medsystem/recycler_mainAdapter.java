package com.example.regis.medsystem;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.regis.medsystem.medicine_book.MedicalBook;


public class recycler_mainAdapter extends RecyclerView.Adapter<recycler_mainAdapter.ViewHolders> {
    Context context;

    public recycler_mainAdapter(Context context) {
        this.context = context;
    }


    private String[] array_title = {"R&D", "Disease", "Medicine", "Books"};
    private int[] drawable_array = {R.drawable.medimage, R.drawable.mymedcover, R.drawable.medimage, R.drawable.medbookcover};


    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_cardview, parent, false);
        return new ViewHolders(v, context);


    }

    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        holder.title.setText(array_title[position]);
        Glide.with(context).load(drawable_array[position]).into(holder.cover_image);
    }

    @Override
    public int getItemCount() {
        return array_title.length;
    }

    static class ViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView cover_image, share, bookmark, fav;
        Context cont;
        TextView title;
        Snackbar snackbar;

        ViewHolders(View view, Context cont) {
            super(view);
            this.cont = cont;
            cover_image = (ImageView) view.findViewById(R.id.medCover_adp);
            title = (TextView) view.findViewById(R.id.title_adp);
            fav = (ImageView) view.findViewById(R.id.fav);
            share = (ImageView) view.findViewById(R.id.share_art);
            bookmark = (ImageView) view.findViewById(R.id.bookmark);
            fav.setOnClickListener(this);
            share.setOnClickListener(this);
            bookmark.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fav:
                    snackbar = Snackbar.make(v, "Fav from " + title.getText(), Snackbar.LENGTH_SHORT)
                            .setAction("fav", null);
                    snackbar.show();
                    break;
                case R.id.share_art:
                    snackbar = Snackbar.make(v, "share from " + title.getText(), Snackbar.LENGTH_SHORT)
                            .setAction("fav", null);
                    snackbar.show();
                    break;
                case R.id.bookmark:
                   /* snackbar = Snackbar.make(v, "bookmark from " + title.getText(), Snackbar.LENGTH_SHORT)
                            .setAction("fav", null);
                    snackbar.show();*/
                    if (title.getText().equals("Books")) {
                        cont.startActivity(new Intent(cont, MedicalBook.class));
                    }
                    break;

            }
        }
    }
}
