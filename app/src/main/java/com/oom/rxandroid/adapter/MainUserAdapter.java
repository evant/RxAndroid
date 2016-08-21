package com.oom.rxandroid.adapter;

import android.support.v7.widget.RecyclerView;

import com.oom.rxandroid.databinding.ItemMainUserBinding;
import com.oom.rxandroid.model.Person;

import java.util.Collection;

/**
 * Created by CcYang on 2016/7/22.
 * Email: 1367496366@qq.com
 */
public class MainUserAdapter extends BaseRecyclerAdapter< Person > {

    public MainUserAdapter( RecyclerView v, Collection< Person > datas, int itemLayoutId ) {
        super( v, datas, itemLayoutId );
    }

    @Override
    public void convert( BaseBindingHolder holder, Person item, int position ) {
    }
}
