package com.example.administrator.learning.main.presenter;

import android.content.Context;

import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.main.common.AccountEntity;
import com.example.administrator.learning.main.model.IAccountManger;
import com.example.administrator.learning.main.view.ISelectSchoolView;

import java.io.File;

/**
 * Created by Administrator on 2018/10/17 0017.
 */

public class SelectSchoolpresnterImp implements ISelectSchoolpresnter{
    public ISelectSchoolView view;
    public IAccountManger manger;
    public Context context;

    public SelectSchoolpresnterImp(ISelectSchoolView view, IAccountManger manger, Context context){
        this.view = view;
        this.manger = manger;
        this.context = context;
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

    @Override
    public void selectSchool(String name, String no) {
        //选择学校后登陆
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccount(SpUtils.getString(context, StaticCost.USER));
        accountEntity.setName(name);
        accountEntity.setNo(no);
        manger.SelectSchool(accountEntity,context);
    }

@Rx_Register
    public void onSelectSchool(String say){
        //选择学校后的方法回调
        if(say.equals("suc")){
            view.showHome();
        }else if(say.equals("notfind")){
            view.say("对不起此学校无您信息，请检查");
        }else if(say.equals("error")){
            view.say("服务器忙，请稍候再试");
        }else if (say.equals("arlearreigster")){
            view.say("对不起,该学号已经被注册了");
        }
    }
}
