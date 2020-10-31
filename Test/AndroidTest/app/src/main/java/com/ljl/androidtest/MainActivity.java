package com.ljl.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ljl.androidtest.Bean.Person;
import com.ljl.androidtest.Bean.Person2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.bt_1)
    Button bt1;
    @BindView(R.id.bt_2)
    Button bt2;
    @BindView(R.id.bt_3)
    Button bt3;
    @BindView(R.id.bt_4)
    Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick({R.id.bt_1, R.id.bt_2, R.id.bt_3, R.id.bt_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.putExtra("name", "Jack");
//                intent.putExtra("number" ,2000010102);
                startActivity(new Intent(MainActivity.this, SecondActivity.class)
                        .putExtra("name", "Jack")
                        .putExtra("number", 20010102));
                break;
            case R.id.bt_2://Serializable 方式实现intent传递对象
                Person person = new Person();
                person.setName("Charlie");
                person.setNumber(20020304);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("person_serializable", person);
                startActivity(intent);
                break;
            case R.id.bt_3:
                Person2 person2 = new Person2();
                person2.setName("Charlie");
                person2.setNumber(20020304);
                intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("person_serializable", person2);
                startActivity(intent);
                break;
            case R.id.bt_4:
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                break;
        }
    }
}
