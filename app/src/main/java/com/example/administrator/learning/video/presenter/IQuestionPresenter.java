package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;

/**
 * Created by Administrator on 2018/11/3 0003.
 */

public interface IQuestionPresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  14:47
     *  @方法说明：获取问答列表
     */
    void getQuestionlist(String courseid);

    String ID = "id";//问题id
    String TITLE = "title";//问题描述标题
    String CONTENT = "content";//问题的详细描述
    String USERS = "user";//提问人
    String TIME = "time";//提问时间
}
