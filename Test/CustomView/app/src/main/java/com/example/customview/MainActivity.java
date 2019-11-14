package com.example.customview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomView c = findViewById(R.id.custom_view);

        c.smoothScrollTo(-400, 0);

        Button button = findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                double a = Double.parseDouble("2.43333");
                Toast.makeText(MainActivity.this, "oooo", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                c.scrollBy(100, 100);
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//
//                startActivity(intent);
//            }
//        });
    }
}
