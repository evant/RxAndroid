package com.oom.rxandroid.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by CcYang on 2016/7/15.
 * Email: 1367496366@qq.com
 */
public class Person extends BaseObservable {

    private String name;
    private String color = "#ff5500";

    @Bindable
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
        notifyPropertyChanged( BR.name );
    }

    @Bindable
    public String getColor() {
        return color;
    }

    public void setColor( String color ) {
        this.color = color;
        notifyPropertyChanged( BR.color );
    }
}
