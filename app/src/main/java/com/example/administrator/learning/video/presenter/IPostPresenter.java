package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;

/**
 * Created by Administrator on 2018/11/3 0003.
 */

public interface IPostPresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  19:22
     *  @方法说明：获取帖子列表
     */
    void getPostlist(String id);
    String ID = "id";//回答id
    String CONTENT = "content";//回复的内容
    String like = "like";//点赞数
    String TIME = "time";//回复时间
    String USER = "user";//回复人
    String PID = "pid";//对应题目id

}
