package com.example.administrator.learning.main.presenter;

import android.content.Context;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.main.common.AccountEntity;

/**
 * Created by Administrator on 2018/10/17 0017.
 */

public interface ILoginpresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/17  16:54
     *  @方法说明：用户登录
     */
    void login(String accoutn, String pwd, Context context);


}
