package com.narwalabhi.assignment.adapters;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.narwalabhi.assignment.ui.BlankFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = ViewPagerAdapter.class.getSimpleName();
    private final int TAB_COUNT = 10;
    private final String[] sources = {"buzzfeed", "cnn", "espn", "fortune", "independent",
            "fox-news", "techcrunch", "bbc-sport", "msnbc", "mtv-news"};

    private final String[] pageTitles = {"Buzzfeed", "CNN", "ESPN", "Fortune", "Independent",
            "Fox News", "Techcrunch", "BBC Sport", "MSNBC", "MTV news"};

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: " + sources[position]);
        return  BlankFragment.newInstance(sources[position]);
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
