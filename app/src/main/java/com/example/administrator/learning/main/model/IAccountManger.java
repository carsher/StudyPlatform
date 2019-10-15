package com.example.administrator.learning.main.model;

import android.content.Context;

import com.example.administrator.learning.main.common.AccountEntity;

import java.io.File;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public interface IAccountManger {
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  16:04
     *  @方法说明：用户登录模块
     */
    void auth(String account, String pwd, Context context);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  16:04
     *  @方法说明：用户注册模块
     */
    void reigster(AccountEntity account);

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  16:04
     *  @方法说明：选择学校后登陆
     */
    void SelectSchool(AccountEntity account,Context context);

    void sendCode(String phone);
}
