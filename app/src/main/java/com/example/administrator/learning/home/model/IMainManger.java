package com.example.administrator.learning.home.model;

import android.content.Context;

/**
 * Created by Administrator on 2018/10/19 0019.
 */

public interface IMainManger {
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/20  15:35
     *  @方法说明：检查用户是否已经登录， 传递上下文对象
     */
    void IsLogin(Context context);
    void getCourse();
}
