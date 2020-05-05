package com.example.libnetwork.cache;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "cache")
public class Cache implements Serializable {
    @NonNull
    @PrimaryKey
    public String key;
    public byte[] data;

//    @TypeConverters({DateConvert.class})
//    public Date date;
}

