package com.example.regis.medsystem;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    String headerFont = "fonts/myfont.ttf";
    String textFont = "fonts/Crimson_Text/CrimsonText-Bold.ttf";

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    String[] headings = {
            "Medicine"
            , "disease"
            , "Generic"

    };
    int[] imageviews = {
            R.drawable.medici_flat
            , R.drawable.unnamed
            , R.drawable.medici_flat

    };
    String[] descriptions = {
            " Lorem ipsum dolor sit amet, consectetur adipiscing elit Dolor sit amet consectetur adipiscing elit duis tristique sollicitudin."


            , "sapiente quas molestias excepturi sint occaecati cupiditate non provident, caecati cupiditate non "
            , "rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus "

    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spla_layout, container, false);


        TextView heading = (TextView) view.findViewById(R.id.Heading);
        TextView description = (TextView) view.findViewById(R.id.description);
        Typeface myType = Typeface.createFromAsset(context.getAssets(), "fonts/DancingScript-Regular.ttf");
        description.setTypeface(myType);
        Typeface myType2 = Typeface.createFromAsset(context.getAssets(), "fonts/CrimsonText-Regular.ttf");
        heading.setTypeface(myType2);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        heading.setText(headings[position]);
        description.setText(descriptions[position]);
        Glide.with(context).load(imageviews[position]).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        RelativeLayout relativeLayout = (RelativeLayout) object;
        container.removeView(relativeLayout);
    }
}
