package com.example.customview.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customview.R;

public class MainActivity extends Activity {

    //    public TittleBar mBar;
    public ListView listView1, listView2;
    private TextView textView1, textView2;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.button);
        bt.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
//        testHorizontalView();
        testSplitView();
//        final CustomView c = findViewById(R.id.custom_view);
//
//        mBar = findViewById(R.id.title_bar);
////        mBar.setTitle("custom titleBar");
//        mBar.setLeftListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "left left left", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mBar.setRightListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "right right right", Toast.LENGTH_SHORT).show();
//            }
//        });

//        Button button = findViewById(R.id.button);
//        button.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                double a = Double.parseDouble("2.43333");
//                Toast.makeText(MainActivity.this, "oooo", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
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

    private void testSplitView() {

    }

    private void testHorizontalView() {
//        listView1 = findViewById(R.id.list_one);
//        listView2 = findViewById(R.id.list_two);
//
//        String[] strings1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
//        String[] strings2 = {"A", "B", "C", "D", "s", "d", "d", "a", "t", "y", "y", "e"};
//
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strings1);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strings2);
//
//        listView1.setAdapter(adapter1);
//        listView2.setAdapter(adapter2);


//        textView1 = findViewById(R.id.text1);
//        textView2 = findViewById(R.id.text2);

    }
}
