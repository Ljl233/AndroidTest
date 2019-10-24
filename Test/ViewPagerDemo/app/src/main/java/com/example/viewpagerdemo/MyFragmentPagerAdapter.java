package com.example.viewpagerdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tabs = {"tab1", "tab2", "tab3"};

    public MyFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (tabs[position]) {
            case "tab1":
                fragment = new Fragment1();
                break;
            case "tab2":
                fragment = new Fragment2();
                break;
            case "tab3":
                fragment = new Fragment3();
                break;
            default:
                fragment = new Fragment3();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
