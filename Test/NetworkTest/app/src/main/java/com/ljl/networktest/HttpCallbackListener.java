package com.ljl.networktest;

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
