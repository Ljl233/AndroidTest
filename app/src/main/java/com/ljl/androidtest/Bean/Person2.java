package com.ljl.androidtest.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Person2 implements Parcelable {
    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person2(){}

    private Person2(Parcel in) {
        this.name = in.readString();
        this.number = in.readInt();

    }

    public static final Creator<Person2> CREATOR = new Creator<Person2>() {
        @Override
        public Person2 createFromParcel(Parcel in) {
            return new Person2(in);
        }

        @Override
        public Person2[] newArray(int size) {
            return new Person2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.number);
    }
}
