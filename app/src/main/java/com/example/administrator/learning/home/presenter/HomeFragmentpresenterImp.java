package com.example.administrator.learning.home.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.home.common.homeEntey;
import com.example.administrator.learning.home.model.IMainManger;
import com.example.administrator.learning.home.view.IHomeFragment;
import com.example.administrator.learning.home.view.IHomeView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/24 0024.
 */

public class HomeFragmentpresenterImp implements IHomeFragmentpresenterImp {
    private IMainManger manger;
    private IHomeFragment view;
    public Context context;
    public HomeFragmentpresenterImp(Context context,IMainManger manger, IHomeFragment view){
        this.context = context;
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

@Rx_Register
public void onsendCourse(homeEntey entiy){
    int state =  entiy.getStatu();
    switch (state){
        case 200:
            //连接正常
            Log.e("HOMEFRAGMENTPRESENTER","连接正常准备显示");
            view.ShowData(entiy);
            break;
        case 400:
            //获取数据失败，提示服务器忙
            Log.e("HOMEFRAGMENTPRESENTER","网络请求失败，请稍后重试");
            view.ShowNoData();
            view.ShowToast("网络请求失败，请稍后重试");
            break;
    }

}
    @Override
    public void getCourse() {
        manger.getCourse();
    }
}
