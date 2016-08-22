package com.oom.rxandroid.viewModel;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.oom.rxandroid.BR;
import com.oom.rxandroid.R;
import com.oom.rxandroid.base.viewModel.ViewModel;
import com.oom.rxandroid.model.Person;

import java.util.concurrent.TimeUnit;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by YoungDroid on 2016/8/21.
 * Email: YoungDroid@163.com
 */
public class MainViewModel implements ViewModel {

    // Context
    private Activity activity;

    // 数据源（adapter）
    public final ObservableList< ItemViewModel > itemViewModels = new ObservableArrayList<>();
    public final ItemViewSelector< ItemViewModel > itemViewSelector = new ItemViewSelector< ItemViewModel >() {
        @Override
        public void select( ItemView itemView, int position, ItemViewModel item ) {
            itemView.set( BR.viewModel, R.layout.item_main_user );
        }

        @Override
        public int viewTypeCount() {
            return 1;
        }
    };

    public MainViewModel( Activity activity ) {
        this.activity = activity;

        Observable.interval( 1, TimeUnit.SECONDS )
                .flatMap( aLong -> Observable.just( aLong * 10 ) )
                .map( aLong1 -> new Person( "Person : " + aLong1 ) )
                .take( 50 )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( person -> {
                    Log.e( "YoungDroid", "create " + person.getName() );
                    itemViewModels.add( new ItemViewModel( activity, person ) );
                } );
    }
}
