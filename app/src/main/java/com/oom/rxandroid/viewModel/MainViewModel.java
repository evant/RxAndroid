package com.oom.rxandroid.viewModel;

import android.app.Activity;
import android.databinding.ObservableField;

import com.oom.rxandroid.base.viewModel.ViewModel;

/**
 * Created by YoungDroid on 2016/8/21.
 * Email: YoungDroid@163.com
 */
public class MainViewModel implements ViewModel {

    // Context
    private Activity activity;

    // data filed
    public final ObservableField< IncludeViewModel > includeViewModel = new ObservableField<>();

    public MainViewModel( Activity activity ) {
        this.activity = activity;
        this.includeViewModel.set( new IncludeViewModel( activity ) );
    }
}
