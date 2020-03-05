package com.ljl.runtimepermissiontest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button make_call, mBtUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        make_call = findViewById(R.id.make_call);
        make_call.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:10086"));
            startActivity(intent);
        });

        mBtUpActivity = findViewById(R.id.bt_up_activity);
        mBtUpActivity.setOnClickListener(v -> {
            startActivity(new Intent(this, UpImageActivity.class));
        });
    }
}
