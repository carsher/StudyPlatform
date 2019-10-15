package com.example.administrator.learning.main.presenter;

import android.util.Log;

import com.example.administrator.learning.common.SendCodeEntity;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.main.common.AccountEntity;
import com.example.administrator.learning.main.common.ReigsterEntity;
import com.example.administrator.learning.main.model.IAccountManger;
import com.example.administrator.learning.main.view.IReigsterView;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class ReigsterpresenterImp implements IReigsterpresenter{
    public IAccountManger manger;
    public IReigsterView view;

    public ReigsterpresenterImp(IAccountManger manger, IReigsterView view){
        this.manger = manger;
        Log.i("初始化；presenter 成功","初始化成功");
        this.view = view;
    }
    /**
     * @param model
     * @创建用户 crash
     * @创建时间 2018/10/16  15:51
     * @方法说明：用户注册
     */
    @Override
    public void registerUser(AccountEntity model) {
        manger.reigster(model);
    }

    @Override
    public void sendCode(String phone) {
        //下发验证码
        manger.sendCode(phone);
    }
    @Rx_Register
    public void onReigster(ReigsterEntity entity){
        //用户注册回调
        switch (entity.getCode()){
            case IReigsterpresenter.USER_EXITS:
                view.say("用户已经存在，请直接登录");
                break;
            case IReigsterpresenter.USER_NETWORD:
                view.say("连接到服务器超时，请重试");
                break;
            case IReigsterpresenter.USER_REIGSET_SUC:
                view.ShowLogin();
                break;
            case IReigsterpresenter.USER_CODEERROR:
                view.say("对不起您输入的验证码有误");
                break;
            case IReigsterpresenter.USER_NOERROR:
                view.say("对不起您输入的学号有误");
                break;
        }
    }
    @Rx_Register
    public void onSendCode(com.example.administrator.learning.main.common.SendCodeEntity entity){
        //下发验证码的方法回调
        switch (entity.getCode()){
            case IReigsterpresenter.SEND_SUC:
                Log.e("发送成功","请填写");
                view.sendCodeSuc();
                break;
            case IReigsterpresenter.SEND_NETWORD:
                Log.e("发送成功","请填写2");
                view.say("连接到服务器超时，请检查您的网络");
                break;
            case IReigsterpresenter.SEND_ERR:
                Log.e("发送成功","请填写3");
                view.say("您输入的手机号有误，请重新输入");
                break;
            case IReigsterpresenter.USER_EXITS:
                Log.e("发送成功","请填写4");
                view.say("该用户已经注册,请直接登录");
                break;
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
