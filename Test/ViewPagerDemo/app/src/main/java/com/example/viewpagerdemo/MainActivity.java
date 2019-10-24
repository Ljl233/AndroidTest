package com.example.viewpagerdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private View view1, view2, view3;
    private List<View> viewList = new ArrayList<>();
    private TabLayout mTabLayout;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    String TAG = "TAG---MainActivity";
    List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTabLayout();
        initViewPager();
        initFragmentPagerAdapter();

//        initViewPagerAdapter();


    }

    private void initFragmentPagerAdapter() {
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), 0);
        mViewPager.setAdapter(myFragmentPagerAdapter);
    }

    private void initTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("tab1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("tab3"));
        //为TabLayout添加Tab选择事件监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {//当标签被选择时回调

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {//当标签从选择变为非选择时回调

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {//当标签被重新选择时回调

            }
        });
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    private void initViewPagerAdapter() {
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                Log.e(TAG, "getCount");
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                Log.e(TAG, "isViewFromObject");
                return view == object;
            }


            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                Log.e(TAG, "destroyItem");
                container.removeView(viewList.get(position));
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                Log.e(TAG, "getItemPosition");
                return super.getItemPosition(object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                Log.e(TAG, "getPageTitle");
                return titleList.get(position);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                Log.e(TAG, "instantiateItem");
                return viewList.get(position);
            }
        };

        mViewPager.setAdapter(pagerAdapter);

    }

    private void initViewPager() {

//        LayoutInflater inflater = getLayoutInflater().from(this);
//        view1 = inflater.inflate(R.layout.view1, null);
//        view2 = inflater.inflate(R.layout.view2, null);
//        view3 = inflater.inflate(R.layout.view3, null);
//        viewList.add(view1);
//        viewList.add(view2);
//        viewList.add(view3);
//        titleList.add("View1");
//        titleList.add("View2");
//        titleList.add("View3");

    }

    private void initView() {
        mViewPager = findViewById(R.id.view_page);
        mTabLayout = findViewById(R.id.tab);
    }
}
