package com.oom.rxandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

/**
 * Created by CcYang on 2016/7/22.
 * Email: 1367496366@qq.com
 */
public class BaseRecyclerUtils {
    public static void setAdapter( BaseRecyclerAdapter recyclerAdapter, LayoutManager layoutManager, RecyclerView recyclerView) {
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( recyclerAdapter );
    }
}
