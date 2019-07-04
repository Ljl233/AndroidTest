package com.ljl.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "receive in my broadcast receive", Toast.LENGTH_SHORT).show();
        abortBroadcast();//截断广播，不允许广播继续传递
    }
}
