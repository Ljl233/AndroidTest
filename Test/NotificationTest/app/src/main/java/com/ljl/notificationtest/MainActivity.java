package com.ljl.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(this, "1")
                        .setContentTitle("This is a content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)//点击后取消notification
                        .build();
                manager.notify(1, notification);



//                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
////为了版本兼容  选择V7包下的NotificationCompat进行构造
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
////Ticker是状态栏显示的提示
//                builder.setTicker("简单Notification");
////第一行内容  通常作为通知栏标题
//                builder.setContentTitle("标题");
////第二行内容 通常是通知正文
//                builder.setContentText("通知内容");
////第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
//                builder.setSubText("这里显示的是通知第三行内容！");
////ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
////builder.setContentInfo("2");
////number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
//                builder.setNumber(2);
////可以点击通知栏的删除按钮删除
//                builder.setAutoCancel(true);
////系统状态栏显示的小图标
//                builder.setSmallIcon(R.mipmap.ic_launcher);
////下拉显示的大图标
//                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
//                Intent intent = new Intent(this, NotificationActivity.class);
//                PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
////点击跳转的intent
//                builder.setContentIntent(pIntent);
////通知默认的声音 震动 呼吸灯
//                builder.setDefaults(NotificationCompat.DEFAULT_ALL);
//                Notification notification = builder.build();
//                manager.notify(1, notification);

                break;
            default:
                break;
        }


    }
}
