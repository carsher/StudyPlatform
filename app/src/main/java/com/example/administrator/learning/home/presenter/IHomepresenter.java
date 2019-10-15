package com.example.administrator.learning.home.presenter;

import android.content.Context;

import com.example.administrator.learning.common.utils.BasePresenter;

/**
 * Created by Administrator on 2018/10/19 0019.
 */

public interface IHomepresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/19  10:45
     *  @方法说明：显示主页
     */
    void ShowHome();
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/19  10:45
     *  @方法说明：现在精选文章<显示运动健身相关文章></>
     */
    void ShowChoice();
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/19  10:45
     *  @方法说明：显示社区
     */
    void ShowStack();
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/19  10:45
     *  @方法说明：显示个人中心
     */
    void ShowUser();
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/19  10:45
     *  @方法说明：检查用户是否登录
     */
//    void IsLogin(Context context);

    void close();


}
