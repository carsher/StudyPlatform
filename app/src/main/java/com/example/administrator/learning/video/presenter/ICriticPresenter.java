package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public interface ICriticPresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/28  14:50
     *  @方法说明：获取某条评论
     */
    void getContentDynamic(String courseId);
     String ID="id";
     String PID="pid";//课程id
     String USER="user";//发布评论人
     String SAY="say";//评论内容
     String TIME="time";//发布时间


}
