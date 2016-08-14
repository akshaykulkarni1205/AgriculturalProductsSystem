package com.os.scau.agriculturalproductssystem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private BannerView bannerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        bannerView = (BannerView) view.findViewById(R.id.bannerView);

        BannerBean b1 = new BannerBean();
        b1.setImg("http://market.h.jaylin.me/Uploads/2016-04-11/570b8c9d95585.jpg");
        b1.setTitle("1");
        BannerBean b2 = new BannerBean();
        b2.setImg("http://market.h.jaylin.me/Uploads/2016-05-23/5742bdb901e12.png");
        b2.setTitle("2");

        List<BannerBean> list = new ArrayList<>();
        //list.add(b2);
        list.add(b1);
        list.add(b2);
        //list.add(b1);
        bannerView.setDatas(list);
        return view;
    }

}
