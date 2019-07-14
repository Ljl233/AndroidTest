package com.ljl.androidnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button button;
    private static final String CHANNEL_ID = "CHANNEL";
    private static final String CHANNEL_NAME = "CHANNEL_NAME";
    private static final String CHANNEL_DESC = "CHANNEL_DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                showNotification();

            }
        });
    }

    private void show(){
        String id = "my_channel_01";
        String name="我是渠道名字";
       NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            Toast.makeText(this, mChannel.toString(), Toast.LENGTH_SHORT).show();
            Log.i("TAG", mChannel.toString());
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this)
                    .setChannelId(id)
                    .setContentTitle("5 new messages")
                    .setContentText("hahaha")
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("5 new messages")
                    .setContentText("hahaha")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true);
                   // .setChannel(id);//无效
            notification = notificationBuilder.build();
        }
        notificationManager.notify(111123, notification);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showNotification() {
        //设置跳转意图:点击通知栏，跳转搜索百度
        Intent intent = new Intent();
        intent.setData(Uri.parse("http://www.baidu.com"));
        intent.setAction(Intent.ACTION_VIEW);
        PendingIntent pi = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //获取系统通知服务，创建manager对象
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //创建notification对象
        Notification notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            Toast.makeText(this, CHANNEL_ID, Toast.LENGTH_LONG).show();
            manager.createNotificationChannel(mChannel);
            notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle("新消息1")
                    .setContentText("Hello world")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setChannelId(CHANNEL_ID)
                    .setContentIntent(pi)//设置pendingIntent,点击通知时就会用到
                    .build();
            Toast.makeText(this,"notification build successfully",Toast.LENGTH_LONG).show();
        } else {
            notification = new NotificationCompat.Builder(this).setContentTitle("新消息2")
                    .setContentText("Hello world")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true)
                    .setChannelId(CHANNEL_ID)
                    .setContentIntent(pi) //设置pe
                    .build();
        }
        if(!manager.areNotificationsEnabled()){
            Toast.makeText(this,"权限未开启",Toast.LENGTH_LONG).show();
        }else manager.notify(0, notification);

    }
}
