package com.seeyou.fastbuild;

import android.content.Context;

import com.seeyou.toolkit.BaseApplication;
import com.seeyou.toolkit.ToolKit;
import com.seeyou.toolkit.constants.ToolKitConfiguration;

/**
 * @author sumn
 * date 2019/10/21
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ToolKit.getInstance().setToolKitConfiguration(new ToolKitConfiguration() {
            @Override
            public String getBaseUrl() {
                return "http://192.168.1.158:8080/Base/";
            }

            @Override
            public Context getContext() {
                return MyApplication.this;
            }
        });
    }
}
