package com.example.administrator.learning.video.presenter;

import android.util.Log;

import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.QuestEntity;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IQuestionFragment;

/**
 * Created by Administrator on 2018/11/3 0003.
 */

public class QuestionPresenter implements IQuestionPresenter{
    private IQuestionFragment view;
    private IVideoManger manger;

    public QuestionPresenter(IQuestionFragment view, IVideoManger manger){
        this.view = view;
        this.manger = manger;
        Log.e("QuestionPresenter初始化成功", "QuestionPresenter: " );
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
    public void getQuestionlist(String courseid) {
        manger.getQuestionList(courseid);
    }


    @Rx_Register
    public void onSendQuestionList(QuestEntity entity){
        if (entity.getState() == 200){
            Log.e("QuestionPresenter回掉了", "onSendQuestionList: "+entity.getState());
            view.ShowData(entity.getList());
        }else{
            Log.e("QuestionPresenter回掉了", "onSendQuestionList: "+entity.getState());
            view.ShowToash("网络请求失败！");
        }

    }
}
