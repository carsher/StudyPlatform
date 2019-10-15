package com.example.administrator.learning.video.presenter;

import android.content.Context;
import android.util.Log;

import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.rxbus.Rx_Register;
import com.example.administrator.learning.video.common.bean.DynamicEntity;
import com.example.administrator.learning.video.model.IVideoManger;
import com.example.administrator.learning.video.view.IAddDynamicView;
import com.example.administrator.learning.video.common.bean.onSendEntity;



/**
 * Created by asdf on 2018/5/6.
 */

public class SendDynamicpresenterImp implements IDynamicpresenter {

    public Context mContext;
    public IVideoManger mModel;
    public IAddDynamicView mView;

    public SendDynamicpresenterImp(Context context, IVideoManger model, IAddDynamicView view){
        this.mContext = context;
        this.mModel = model;
        this.mView = view;
        Log.e("初始化成功", "SendDynamicpresenterImp: ");

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
        mView = null;
    }


    @Override
    public void sendDynamicJustText(DynamicEntity entity) {
        mModel.sendDynamicJust(entity);
    }

    @Rx_Register
    public void onSend(onSendEntity entity){
        if (entity.getStatu() == CommonRespones.STATE_SUC_CODE){
            Log.e("SendDynamImp回掉了", "onSend: "+entity.getStatu());
            mView.onSend(200,entity.getRequestId());
        }else{
            Log.e("SendDynamImp回掉了", "onSend: "+entity.getStatu());
            mView.showToast("发布失败！");
        }
    }
}
