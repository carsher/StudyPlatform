package com.example.administrator.learning.video.presenter;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.onCriticEntity;
import com.example.administrator.learning.video.common.bean.videoEnter;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.ICriticFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/10/28 0028.
 */

public class GetCriticpresenter implements ICriticPresenter{
    public IVideoManger manger;
    public ICriticFragment view;

    public GetCriticpresenter(IVideoManger manger, ICriticFragment view){
        this.manger = manger;
        this.view = view;
        Log.e("GETCriticPresenter", "GETCriticpresenter: 初始化成功" );
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
        view=null;
    }

    @Override
    public void getContentDynamic(String courseID) {
        manger.getContentDynamic(courseID);
    }

    @Rx_Register
    public void  onsendContenDynamic(onCriticEntity entity){
        if (entity.getState() == 200){
            Log.e("GetCriticpresenter", "onsendData: "+entity.getState());
            view.ShowContentDynamic(entity.getList());
        }else{
            Log.e("GetCriticpresenter", "onsendData: "+entity.getState());
            view.ShowToash("服务器，正在维护中！");
        }

    }

}
