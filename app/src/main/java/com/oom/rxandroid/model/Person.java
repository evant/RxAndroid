package com.oom.rxandroid.model;

/**
 * Created by CcYang on 2016/7/15.
 * Email: 1367496366@qq.com
 */
public class Person {

    private String name;
    private String color = "#ff5500";

    public Person() {}

    public Person( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor( String color ) {
        this.color = color;
    }
}
