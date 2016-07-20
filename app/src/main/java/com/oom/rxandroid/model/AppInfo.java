package com.oom.rxandroid.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by CcYang on 2016/7/7.
 * Email: 1367496366@qq.com
 */
@Data
@Accessors(prefix = "m")
public class AppInfo implements Comparable< Object > {
    long mLastUpdateTime;
    String mName;
    String mIcon;

    public AppInfo( long mLastUpdateTime, String mName, String mIcon ) {
        this.mLastUpdateTime = mLastUpdateTime;
        this.mName = mName;
        this.mIcon = mIcon;
    }

    @Override
    public int compareTo( Object another ) {
        return getName().compareTo( ( ( AppInfo ) another ).getName() );
    }
}
