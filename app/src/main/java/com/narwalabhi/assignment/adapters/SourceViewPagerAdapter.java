package com.narwalabhi.assignment.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SourceViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private static final int TAB_COUNT = 10;

    public SourceViewPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
