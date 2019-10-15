package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.video.common.bean.onPostEntity;

/**
 * Created by Administrator on 2018/11/5 0005.
 */

public interface IAddPostPresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  14:47
     *  @方法说明：回复问题
     */
    void geReply(onPostEntity entity);

}
