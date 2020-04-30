package com.example.libnetwork;

//不写interface的原因就是可以选择性地复写方法
public abstract class JsonCallback<T> {
    public void onSuccess(ApiResponse<T> response) {

    }

    public void onError(ApiResponse<T> response) {

    }

    public void onCacheSuccess(ApiResponse<T> response){

    }
}
