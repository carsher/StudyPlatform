package com.example.administrator.learning.video.presenter;

import android.view.View;

import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.TestEntity;
import com.example.administrator.learning.video.common.bean.onTestState;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.ITestFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/10/30 0030.
 */

public class TestPresenter implements ITestPresenter{
    private IVideoManger manger;
    private ITestFragment view;
    public TestPresenter(IVideoManger manger,ITestFragment view){
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
    public void sendTest(onTestState state){
        if (state.getState() == 200){
            view.ShowTest(state.getList());
        }else {
            view.ShowToash("试题教师正在制作中......");
        }
    }


//获取试题
    @Override
    public void getTestWord(String testid) {
        manger.getTestWord(testid);
    }
}
