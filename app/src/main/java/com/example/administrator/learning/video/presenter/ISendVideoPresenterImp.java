package com.example.administrator.learning.video.presenter;

import android.util.Log;

import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.videoEnter;
import com.example.administrator.learning.video.model.IVideoManger;

import java.util.List;

public class ISendVideoPresenterImp implements ISendVideoPresenter {
    private IVideoManger manger;

    public ISendVideoPresenterImp(IVideoManger manger){
        this.manger = manger;
        Log.e("加载成功", "ISendVideoPresenterImp: ");
    }
    @Override
    public void sendVideoHistory(String sid,String name,String user,String classname) {
        manger.sendVideoHistory(sid,name,user,classname);
    }
    @Rx_Register
    public void onsendData(String statu){
        Log.e("ISENDVIDEOPRESENTERIMP","ISendVideoPresenterImp:"+statu);
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

    }
}
