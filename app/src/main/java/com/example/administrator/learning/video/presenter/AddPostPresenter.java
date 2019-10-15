package com.example.administrator.learning.video.presenter;

import android.util.Log;

import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.onPostEntity;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IAddPostActivity;

/**
 * Created by Administrator on 2018/11/5 0005.
 */

public class AddPostPresenter implements IAddPostPresenter{
    private IVideoManger manger;
    private IAddPostActivity view;
    public AddPostPresenter(IVideoManger manger,IAddPostActivity view){
        this.manger = manger;
        this.view = view;
        Log.e("ADDPOSTPRESENTER", "AddPostPresenter:初始化成功了");
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
    public void geReply(onPostEntity entity) {
        manger.getReply(entity);
    }
    @Rx_Register
    public  void onsendReply(String state){
        if (state.equals("success")){
            Log.e("onsendReply回调了", "onsendReply: "+state+"ddd");
            view.ShowData();
        }else{
            Log.e("onsendReply回调了", "onsendReply: "+state+"ddd");
            view.ShowToash("回复失败！");
        }
    }
}
