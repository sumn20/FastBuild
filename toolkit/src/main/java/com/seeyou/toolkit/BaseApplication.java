package com.seeyou.toolkit;

import android.app.Application;

import com.seeyou.toolkit.utils.Utils;

/**
 * @author sumn
 * date 2019/10/21
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
