package com.example.libnetwork;

import androidx.annotation.IntDef;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @param <T> response 实体类型
 * @param <R> request的子类
 */
public abstract class Request<T, R extends Request> {

    private String mUrl;
    private HashMap<String, String> headers = new HashMap<>();
    protected HashMap<String, Object> params = new HashMap<>();

    public static final int CACHE_ONLY = 1;
    //先访问缓存，同时发起网络请求，成功后缓存到本地
    public static final int CACHE_FIRST = 2;
    public static final int NET_ONLY = 3;
    //先访问网络，成功后缓存到本地
    public static final int NET_CATCH = 4;
    private String cacheKey;

    @IntDef({CACHE_ONLY, CACHE_FIRST, NET_ONLY, NET_CATCH})
    public @interface CacheStrategy {

    }

    public Request(String url) {
        // user/list
        mUrl = url;
    }

    public R addHeader(String key, String value) {
        headers.put(key, value);
        return (R) this;
    }

    public void execute(){

    }

    public R addParam(String key, Object value) {
        try {
            Field field = value.getClass().getField("TYPE");
            Class claz = (Class) field.get(null);
            if (claz.isPrimitive()) {
                params.put(key, value);
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (R) this;
    }

    public R cacheKey(String key) {
        this.cacheKey = key;
        return (R) this;
    }
}
