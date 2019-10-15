package com.example.administrator.learning;

import android.app.Application;

import org.xutils.x;


/**
 * Created by Administrator on 2018/11/8 0008.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化XUtils
        x.Ext.init(this);
        //设置debug模式
        x.Ext.setDebug(true);
    }

}
