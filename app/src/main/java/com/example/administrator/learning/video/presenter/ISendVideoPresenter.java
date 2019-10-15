package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.video.common.bean.QlayEnter;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public interface ISendVideoPresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/23  10:04
     *  @方法说明：SID
     */
    void sendVideoHistory(String sid,String name,String user,String classname);



}
