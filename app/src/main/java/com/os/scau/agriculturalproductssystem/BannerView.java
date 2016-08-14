package com.os.scau.agriculturalproductssystem;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lenovo on 2016/7/27.
 */
public class BannerView extends FrameLayout {

    //@BindView(R.id.banner_viewpager)
    ViewPager bannerViewpager;
    //@BindView(R.id.ll_banner_dots)
    LinearLayout llBannerDots;
    //@BindView(R.id.tv_banner_title)
    TextView tvBannerTitle;
    private List<ImageView> imgViews;
    private Context context;
    private boolean stop;
    private int pageIndex = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    bannerViewpager.setCurrentItem(bannerViewpager.getCurrentItem()+1);
                    break;
            }
        }
    };
    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bannerview, this);
        llBannerDots = (LinearLayout) view.findViewById(R.id.ll_banner_dots);
        bannerViewpager = (ViewPager) view.findViewById(R.id.banner_viewpager);
        tvBannerTitle = (TextView) view.findViewById(R.id.tv_banner_title);
        imgViews = new ArrayList<>();
    }
    public void setDatas(final List<BannerBean> bannerlist){
        //前后增加两张图片
        bannerlist.add(bannerlist.get(0));
        bannerlist.add(0,bannerlist.get(bannerlist.size()-2));

        final int size = bannerlist.size();
        for(int i = 0;i < size;i++){
                imgViews.add(getImageView(bannerlist.get(i).getImg()));
            //点比图片少两个
            if(i < size-2) {

                    llBannerDots.addView(getDot(i));
                }
            if(i == 1){
                tvBannerTitle.setText(bannerlist.get(i).getTitle());
            }
        }

        bannerViewpager.setAdapter(new BannerViewAdapter(imgViews));
        //从1开始
        bannerViewpager.setCurrentItem(1,false);
        bannerViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            for(int i = 0;i < size;i++){
                ImageView img = (ImageView) llBannerDots.getChildAt(i);
                if(img != null){
                    img.setEnabled(false);
                    if(position-1 == i){
                        img.setEnabled(true);
                    }
                }
            }
                tvBannerTitle.setText(bannerlist.get(position).getTitle());
                if(position == size -1){
                    //滑到最后一个的时候跳转到第一个
                    bannerViewpager.setCurrentItem(1,false);
                }
                if(position == 0){
                    //滑到第一个跳转到倒数第二个
                    bannerViewpager.setCurrentItem(size-2,false);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if(!(size <=1)) startBanner(size);

    }



    private ImageView getDot(int i) {
        ImageView dot = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15, 15);
        layoutParams.leftMargin = 5;
        dot.setLayoutParams(layoutParams);
        dot.setImageResource(R.drawable.indicator_imageview_selector);
        dot.setEnabled(false);
        if(i == 0){
            dot.setEnabled(true);
        }
        return dot;
    }

    private ImageView getImageView(String url) {
        ImageView img = new ImageView(context);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        MyApplication.getImageLoader().displayImage(url,img);
        return img;
    }
    private void startBanner(final int size) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    SystemClock.sleep(5000);
//                    if(pageIndex == size-1){
//                        pageIndex = -1;
//                    }
//                    pageIndex++;
                    handler.sendEmptyMessage(1);
                }
            }
        }).start();
    }
}
