package com.theminimaldeveloper.superheroes;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Arrays;

public class CharViewPagerAdapter extends PagerAdapter {

     Context context;
    private String[] charNames;
    private int[] imageIds;
     LayoutInflater layoutInflater;


    public CharViewPagerAdapter(Context context, String[] charNames, int[] imageIds) {
        this.context = context;
        this.charNames = charNames;
        this.imageIds = imageIds;

        layoutInflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }




    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ConstraintLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        View view = layoutInflater.inflate(R.layout.char_vp_item, container ,false);
        ImageView img_char = (ImageView) view.findViewById(R.id.img_char);
        TextView txt_char_name = (TextView) view.findViewById(R.id.txt_char_name);

        img_char.setImageResource(imageIds[position]);
        txt_char_name.setText(charNames[position]);

        container.addView(view);

        img_char.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CharacterDetails.class);
                intent.putExtra("characterName", charNames[position]);
                context.startActivity(intent);

            }
        });

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
