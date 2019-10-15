package com.example.administrator.learning.main.presenter;

import android.widget.BaseAdapter;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.main.common.AccountEntity;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public interface IReigsterpresenter extends BasePresenter{
    public static final int USER_CODEERROR = 7;

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  15:23
     *  @方法说明：用户注册
     */
    void registerUser(AccountEntity model);
    void sendCode(String phone);

    public static final int USER_EXITS = 0;
    public static final int USER_REIGSET_SUC = 2;
    public static final int USER_NETWORD = 1;
    public static final int USER_NOERROR = 3;

    public static final int SEND_NETWORD = 4;
    public static final int SEND_SUC = 5;
    public static final int SEND_ERR = 6;
}
