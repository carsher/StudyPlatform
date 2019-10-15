package com.example.administrator.learning.home.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.home.model.IMainManger;
import com.example.administrator.learning.home.view.IHomeView;
import com.example.administrator.learning.main.common.AccountEntity;

/**
 * Created by Administrator on 2018/10/19 0019.
 */

public class HomepresenterImp implements IHomepresenter{
    private IMainManger manger;
    private IHomeView view;

    public HomepresenterImp(IMainManger manger,IHomeView view){
        this.manger = manger;
        this.view = view;
    }

    @Override
    public void register() {
        RxDao.getInstance().register(this);
    }

    @Override
    public void unregister() {
        RxDao.getInstance().unregister(this);

    }

    @Override
    public void onDestroy() {
        view = null;

    }
    /**
     * @创建用户 crash
     * @创建时间 2018/10/19  10:47
     * @方法说明：显示主页
     */
    @Override
    public void ShowHome() {
        Log.i("执行了Homepresenter中的方法：","显示首页");
        view.ShowFragment(1);
    }

    /**
     * @创建用户 crash
     * @创建时间 2018/10/19  10:47
     * @方法说明：显示记录
     */
    @Override
    public void ShowChoice() {
        Log.i("执行了Homepresenter中的方法：","显示记录");
        view.ShowFragment(3);

    }
    /**
     * @创建用户 crash
     * @创建时间 2018/10/19  10:47
     * @方法说明：显示下载
     */
    @Override
    public void ShowStack() {
        Log.i("执行了Homepresenter中的方法：","显示下载");
        view.ShowFragment(2);
    }
    /**
     * @创建用户 crash
     * @创建时间 2018/10/19  10:51
     * @方法说明：显示我的
     */
    @Override
    public void ShowUser() {
        Log.i("执行了Homepresenter中的方法：","显示社区");
        view.ShowFragment(4);
    }
    /**
     * @创建用户 crash
     * @创建时间 2018/10/19  10:51
     * @方法说明：检查用户是否登录
     */
//    @Override
//    public void IsLogin(Context context) {
//        Log.i("调用islogin犯法","检查用户是否登录");
//        manger.IsLogin(context);
//    }

    @Override
    public void close() {
        view.close();
    }


}
