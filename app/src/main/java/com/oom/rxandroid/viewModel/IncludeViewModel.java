package com.oom.rxandroid.viewModel;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.util.Log;
import android.widget.Toast;

import com.kelin.mvvmlight.command.ReplyCommand;
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
 * Created by YoungDroid on 2016/8/22.
 * Email: YoungDroid@163.com
 */
public class IncludeViewModel implements ViewModel {

    // Context
    private Activity activity;

    // data filed
    public final ObservableField< String > someWords = new ObservableField<>();

    // item viewModel
    public final ObservableList< ItemViewModel > itemViewModels = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of( BR.itemViewModel, R.layout.item_main_user );

    // Command
    public final ReplyCommand onTitleClick = new ReplyCommand( () -> {
        Toast.makeText( activity, "Click title", Toast.LENGTH_SHORT ).show();
        itemViewModels.add( new ItemViewModel( activity, new Person( "PersonYoung" ) ) );
    } );

    public IncludeViewModel( Activity activity ) {
        this.activity = activity;

        someWords.set( "YoungDroid" );

        Observable.interval( 100, TimeUnit.MILLISECONDS )
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
