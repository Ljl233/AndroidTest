package com.example.roomdemo.data;


import androidx.annotation.NonNull;
import androidx.room.*;

//使用entity注解指定表的名字
@Entity(tableName = "word_table")
public class Word {
    //primaryKey注解指定主键
    //PrimaryKey(autoGenerate=true) will generate integer automatically by SQLite,default to false

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")//set column name if you don't want to use the name of member variable

    private String mWord;

    public Word(String word) {
        this.mWord = word;
    }

    public String getWord() {
        return this.mWord;
    }
}