package com.oom.rxandroid.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.oom.rxandroid.databinding.ItemMainUserBinding;
import com.oom.rxandroid.databinding.ViewStubLeftBinding;
import com.oom.rxandroid.databinding.ViewStubRightBinding;
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
        ItemMainUserBinding binding = ( ItemMainUserBinding ) holder.getBinding();

        binding.vsLeft.setOnInflateListener( ( viewStub, view ) -> {
            ViewStubLeftBinding leftBinding = DataBindingUtil.bind( view );
            leftBinding.setPerson( item );
        } );
        binding.vsRight.setOnInflateListener( ( viewStub, view ) -> {
            ViewStubRightBinding rightBinding = DataBindingUtil.bind( view );
            rightBinding.setPerson( item );
        } );

        if ( !binding.vsLeft.isInflated() ) binding.vsLeft.getViewStub().inflate();
        if ( !binding.vsRight.isInflated() ) binding.vsRight.getViewStub().inflate();

        ViewStub viewStubLeft = binding.vsLeft.getViewStub();
        ViewStub viewStubRight = binding.vsRight.getViewStub();

        if ( viewStubLeft != null && viewStubRight != null ) {
            if ( position % 2 == 0 ) {
                viewStubLeft.setVisibility( View.VISIBLE );
                viewStubRight.setVisibility( View.INVISIBLE );
            } else {
                viewStubLeft.setVisibility( View.INVISIBLE );
                viewStubRight.setVisibility( View.VISIBLE );
            }
        }
    }
}
