package com.example.libnetwork.cache;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConvert {

    @TypeConverter
    public static Long date2Long(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public static Date long2Date(Long l) {
        return new Date(l);
    }
}