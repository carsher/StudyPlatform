package com.example.administrator.learning.video.presenter;

import android.util.Log;

import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.PostEntiy;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IPostActivity;

/**
 * Created by Administrator on 2018/11/3 0003.
 */

public class PostPresenter implements IPostPresenter{
    private IPostActivity view;
    private IVideoManger manger;

    public PostPresenter(IVideoManger manger,IPostActivity view){
        this.manger = manger;
        this.view = view;
        Log.e("POSTPRESENTER初始化成功", "PostPresenter: ");
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
    public void getPostlist(String id) {
        manger.getPostlist(id);
    }
@Rx_Register
    public void onSendPostlist(PostEntiy entiy){
        if (entiy.getState() == 200){
            Log.e("PostPresenter回掉了", "onSendPostlist: "+entiy.getState()+"------"+entiy.getList().size());
         view.ShowData(entiy.getList());
        }else{
            Log.e("PostPresenter回掉了", "onSendPostlist: "+entiy.getState() );
            view.ShowToash("网络请求失败！");
        }
    }
}
