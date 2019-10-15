package com.example.administrator.learning.video.presenter;

import android.util.Log;

import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.QlayEnter;
import com.example.administrator.learning.video.common.bean.videoEnter;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IQlayer_activity;

import java.util.List;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public class playerPresenterImp implements IplayerPresenter{
    private IQlayer_activity view;
    private IVideoManger manger;

    public playerPresenterImp(IVideoManger manger, IQlayer_activity view){
        this.manger = manger;
        this.view = view;
        Log.e("加载成功", "playerPresenterImp: " );
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
    public void ShowSection() {
view.ShowFragment(1);
    }

    @Override
    public void ShowCritic() {
view.ShowFragment(2);
    }

    @Override
    public void ShowTest() {
        view.ShowFragment(3);
    }

    @Override
    public void ShowQuestion() {
view.ShowFragment(4);
    }

    @Override
    public void repuestVideo(QlayEnter enter) {
        manger.requestVideo(enter);
    }

    @Rx_Register
    public void onsendData(videoEnter entity){
        Log.e("playerPresenterImp", "onsendData: "+"k课程命名称"+entity.getCourseName()+""+entity.getState());
        if(entity.getState() == CommonRespones.STATE_SUC_CODE){
            Log.e("playerPresenterImp", "onsendData: "+"k课程命名称");
            view.showSectionData(entity);
        }else if(entity.getState() == CommonRespones.STATE_EEROER_CODE){
            view.ShowToast("网络请求失败，请重试");
        }
    }

}
