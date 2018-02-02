package com.example.regis.medsystem.medicine_book;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class SpaceDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        parent.setClipToPadding(false);
        outRect.left = space;
        outRect.right = space;
        outRect.top = space / 2;
/*
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space/2;
            outRect.bottom=space/2;
        }
        else{
            outRect.top=0;
        }*/

    }
}
