package com.oom.rxandroid.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CcYang on 2016/7/22.
 * Email: 1367496366@qq.com
 */
public abstract class BaseRecyclerAdapter< T > extends RecyclerView.Adapter< BaseBindingHolder > {
    protected List< T > listData;
    protected final int mItemLayoutId;
    protected Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick( View view, Object data, int position );
    }

    public BaseRecyclerAdapter( RecyclerView v, Collection< T > datas, int itemLayoutId ) {
        if ( datas == null ) {
            listData = new ArrayList<>();
        } else if ( datas instanceof List ) {
            listData = ( List< T > ) datas;
        } else {
            listData = new ArrayList<>( datas );
        }
        mItemLayoutId = itemLayoutId;
        context = v.getContext();
    }

    @Override
    public BaseBindingHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        ViewDataBinding binding = DataBindingUtil.inflate( LayoutInflater.from( parent.getContext() ), mItemLayoutId, parent, false );
        BaseBindingHolder holder = new BaseBindingHolder( binding.getRoot() );
        holder.setBinding( binding );
        return holder;
    }

    public abstract void convert( BaseBindingHolder holder, T item, int position );

    @Override
    public void onBindViewHolder( BaseBindingHolder holder, int position ) {
        convert( holder, listData.get( position ), position );
        holder.itemView.setOnClickListener( getOnClickListener( position ) );
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public T getItem(int position) {
        return listData.get( position );
    }

    public void setOnItemClickListener( OnItemClickListener l ) {
        listener = l;
    }

    public View.OnClickListener getOnClickListener( final int position ) {
        return v -> {
            if ( listener != null && v != null ) {
                listener.onItemClick( v, listData.get( position ), position );
            }
        };
    }

    public BaseRecyclerAdapter< T > refresh( Collection< T > datas ) {
        if ( datas == null ) {
            listData = new ArrayList<>();
        } else if ( datas instanceof List ) {
            listData = ( List< T > ) datas;
        } else {
            listData = new ArrayList<>( datas );
        }
        return this;
    }
}
