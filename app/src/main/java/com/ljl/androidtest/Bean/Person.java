package com.ljl.androidtest.Bean;

import java.io.Serializable;

public class Person implements Serializable {

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
}
