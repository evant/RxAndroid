package com.oom.rxandroid;

import android.databinding.BindingConversion;

import com.oom.rxandroid.viewModel.IncludeViewModel;

public class BindingAdapters {

    @BindingConversion
    public static String toString(IncludeViewModel includeViewModel) {
        // crashes because includeViewModel is null.
        return includeViewModel.someWords;
    }
}
