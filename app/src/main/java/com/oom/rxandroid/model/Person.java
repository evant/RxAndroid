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

    @Bindable
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
//        notifyPropertyChanged( BR.name );
    }
}
