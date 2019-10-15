package com.example.administrator.learning.main.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.main.common.AccountEntity;
import com.example.administrator.learning.main.model.IAccountManger;
import com.example.administrator.learning.main.view.ILoginView;

/**
 * Created by Administrator on 2018/10/17 0017.
 */

public class LoginpresenterImp implements ILoginpresenter{
    public IAccountManger manger;
    public ILoginView view;

    public LoginpresenterImp(IAccountManger manger, ILoginView view){
        this.manger = manger;
        Log.i("初始化；presenter 成功","初始化成功");
        this.view = view;
    }
    /**
     * @param accoutn
     * @param pwd
     * @创建用户 crash
     * @创建时间 2018/10/17  17:04
     * @方法说明：用户登录
     */
    @Override
    public void login(String accoutn, String pwd, Context context) {
        manger.auth(accoutn, pwd, context);  //执行登录方法
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/17  17:00
     *  @方法说明：用户登录后的回调方法
     */
    @Rx_Register
    public void OnLogin(AccountEntity account){
        Log.e("打印state",account.getState()+"test");
        if(account.getState() == 200){
            //登录成功。判断是否已经绑定学校了
            String is = account.getisbind();
            if(is.equals("0")){
                //跳转到完善用户页面
             //   view.ShowPullSchool();//提示用户进入选择学校的页面
                view.ShowHome();
            }else{
                    view.ShowHome();
            }
        }else if (account.getState() == 202){
            //除学生以外的登录
            view.ShowHome();
        }else if(account.getState() == 401){
            //提示用户没有该用户
            view.ShowToast("帐号或者密码错误，请重试");
        }else  if(account.getState() == 402){
            view.ShowToast("帐号或者密码错误，请重试");
        }else if( account.getState() == 201){
            view.ShowToast("服务器忙，请稍候重试");
        }
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
}
