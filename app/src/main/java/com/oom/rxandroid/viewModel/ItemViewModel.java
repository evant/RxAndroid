package com.oom.rxandroid.viewModel;

import android.app.Activity;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;

import com.oom.rxandroid.base.viewModel.ViewModel;
import com.oom.rxandroid.model.Person;

/**
 * Created by YoungDroid on 2016/8/21.
 * Email: YoungDroid@163.com
 */
public class ItemViewModel implements ViewModel {

    // Context
    private Activity activity;

    // model
    private Person person;

    // data filed
    public final ObservableField< String > name = new ObservableField<>();

    public ViewStyle viewStyle = new ViewStyle();

    public static class ViewStyle {
        public final ObservableInt textColor = new ObservableInt();
    }

    // Command

    public ItemViewModel( Activity activity, Person person ) {
        this.activity = activity;
        this.person = person;
        this.viewStyle.textColor.set( Color.parseColor( "#000000" ) );

        name.set( person.getName() );
    }
}
