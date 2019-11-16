package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class StyleActivity extends AppCompatActivity {

    List<Integer> mSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);

        initData();
    }

    private void initData() {
        mSrc = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            mSrc.add(R.drawable.ic_launcher_background);

    }
}
