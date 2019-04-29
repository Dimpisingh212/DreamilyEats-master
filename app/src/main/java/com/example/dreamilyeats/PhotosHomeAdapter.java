package com.example.dreamilyeats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class PhotosHomeAdapter extends PagerAdapter {

    Context context;
    private ArrayList<String> photosUrl = new ArrayList<>();
    private Integer [] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.offers_image_4,R.drawable.slider4,R.drawable.slide5,R.drawable.slide6,
                                    R.drawable.slider4,R.drawable.slide2};


    public PhotosHomeAdapter(FragmentManager childFragmentManager, ArrayList<String> photosUrl, FragmentActivity activity) {
        this.photosUrl = photosUrl;
        this.context=activity;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_custom_activity, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        //imageView.setImageResource(images[position]);
        Glide.with(context).load(images[position]).into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);



    }


}
