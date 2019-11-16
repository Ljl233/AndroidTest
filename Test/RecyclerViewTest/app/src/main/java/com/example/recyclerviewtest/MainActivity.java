package com.example.recyclerviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtAnim, mBtStyle, mBtStub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtAnim = findViewById(R.id.bt_anim);
        mBtStyle = findViewById(R.id.bt_style);
        mBtStub = findViewById(R.id.bt_stub);

        initListener();
    }

    void initListener() {
        mBtStub.setOnClickListener(this);
        mBtAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimActivity.class);
                startActivity(intent);
            }
        });

        mBtStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StyleActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_stub:
                startActivity(new Intent(MainActivity.this, ViewStubActivity.class));
                break;
            default:
                break;
        }
    }
}
