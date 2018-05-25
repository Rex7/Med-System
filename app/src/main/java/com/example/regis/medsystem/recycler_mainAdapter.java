package com.example.regis.medsystem;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.regis.medsystem.medicine.MedicineActivity;
import com.example.regis.medsystem.medicine_book.MedicalBook;
import com.example.regis.medsystem.research.ResearchAndDevelop;


class recycler_mainAdapter extends RecyclerView.Adapter<recycler_mainAdapter.ViewHolders> {
    private Context context;
    SessionManage sessionManage;

    recycler_mainAdapter(Context context) {
        this.context = context;

    }


    private String[] array_title = {"R&D", "Medicine", "Books"};
    private int[] drawable_array = {R.drawable.medimage, R.drawable.medimage, R.drawable.medimage, R.drawable.medbookcover};


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
        private final SessionManage sessionManage;
        ImageView cover_image;
        Context cont;
        Button explore;
        TextView title;
        Snackbar snackbar;

        ViewHolders(View view, Context cont) {
            super(view);
            this.cont = cont;
            cover_image = (ImageView) view.findViewById(R.id.medCover_adp);
            title = (TextView) view.findViewById(R.id.title_adp);
            explore = (Button) view.findViewById(R.id.explore);
            explore.setOnClickListener(this);
            sessionManage = new SessionManage(cont.getApplicationContext());


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.explore:
                    if (title.getText().equals("Medicine")) {
                        cont.startActivity(new Intent(cont, MedicineActivity.class));
                    } else if (title.getText().equals("Books")) {

                        if (sessionManage.isLogedIn()) {

                            cont.startActivity(new Intent(cont, MedicalBook.class));
                        } else {
                            Snackbar snackbar = Snackbar.make(v, "You need to Login to access this content", Snackbar.LENGTH_LONG)
                                    .setAction("Need to Login", null);
                            snackbar.show();
                        }

                    } else if (title.getText().equals("R&D")) {
                        cont.startActivity(new Intent(cont, ResearchAndDevelop.class));
                    }

                    break;

            }
        }
    }
}
