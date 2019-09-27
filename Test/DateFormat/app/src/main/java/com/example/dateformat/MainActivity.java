package com.example.dateformat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.Time;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public EditText editText;
    public TextView textView, t1, t2, t3;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.ed);
        textView = findViewById(R.id.textView);
        t1 = findViewById(R.id.date1);
        t2 = findViewById(R.id.date2);
        t3 = findViewById(R.id.date3);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E,M dd, yyyy");
        Date date = new Date();

        t1.setText(simpleDateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        //年
        int year = calendar.get(Calendar.YEAR);
//月
        int month = calendar.get(Calendar.MONTH) + 1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//获取系统时间
//小时
//小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//分钟
        int minute = calendar.get(Calendar.MINUTE);
//秒
        int second = calendar.get(Calendar.SECOND);
   //     Calendar获取当前日期" + year + "年" + month + "月" + day + "日" + hour + ":" + minute + ":" + second

        t2.setText(new DateFormat(date).MyFormat());


        button = findViewById(R.id.button);
        button.setOnClickListener(v -> onClick());

    }


    public void onClick() {
    }
}
