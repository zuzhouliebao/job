package com.example.heartbeat.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.heartbeat.entity.AdImage;
import com.example.heartbeat.fragment.AdBannerFragment;

import java.util.ArrayList;
import java.util.List;

public class AdBannerAdapter extends FragmentStatePagerAdapter {
    private List<AdImage> adImages;


    public AdBannerAdapter(FragmentManager fm) {
        this(fm, null);
        this.adImages = new ArrayList<>();
    }

    public AdBannerAdapter(FragmentManager fm, List<AdImage> adImages) {
        super(fm);
        this.adImages = adImages;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if (adImages.size() > 0) {
            args.putString("ad", adImages.get(position % adImages.size()).getImg());
        }
        return AdBannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    // 防止刷新时显示缓存数据
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    /**
     * 返回数据集的真实容量大小
     */
    public int getSize() {
        return adImages.size();
    }
}
