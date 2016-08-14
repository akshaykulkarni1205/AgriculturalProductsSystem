package com.os.scau.agriculturalproductssystem;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by lenovo on 2016/7/23.
 */
public class BannerViewAdapter extends PagerAdapter {
    private List<ImageView> imgViews;
    public BannerViewAdapter(List<ImageView> imgViews){
       this.imgViews = imgViews;
    }
    @Override
    public int getCount() {
        return imgViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(imgViews.get(position));
        return imgViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgViews.get(position));
    }
}
