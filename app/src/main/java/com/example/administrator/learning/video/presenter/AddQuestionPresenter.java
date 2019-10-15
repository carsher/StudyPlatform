package com.example.administrator.learning.video.presenter;

import android.util.Log;

import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.onPostEntity;
import com.example.administrator.learning.video.common.bean.onQuestionEntity;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IAddPostActivity;
import com.example.administrator.learning.video.view.IAddQuestion;

/**
 * Created by Administrator on 2018/11/5 0005.
 */

public class AddQuestionPresenter implements IAddQuestionPresenter{
    private IVideoManger manger;
    private IAddQuestion view;
    public AddQuestionPresenter(IVideoManger manger, IAddQuestion view){
        this.manger = manger;
        this.view = view;
        Log.e("AddQuestionPresenter", "AddQuestionPresenter:初始化成功了");
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
    public  void onsendReply(String state){
        if (state.equals("success")){
            Log.e("onsendReply回调了", "onsendReply: "+state+"ddd");
            view.ShowData("发布成功！");
        }else{
            Log.e("onsendReply回调了", "onsendReply: "+state+"ddd");
            view.ShowToash("回复失败！");
        }
    }

    @Override
    public void getReplyQuestion(onQuestionEntity entity) {
        manger.getReplyQuestion(entity);
    }
}
