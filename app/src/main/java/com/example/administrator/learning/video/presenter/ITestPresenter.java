package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.video.common.bean.TestEntity;

/**
 * Created by Administrator on 2018/10/30 0030.
 */

public interface ITestPresenter extends BasePresenter{
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/30  9:24
     *  @方法说明：获取试题
     */
    void getTestWord(String testid);
    String TESTID = "id";//题目id
    String PID = "pid";//对应小节的id
    String A = "a";//a
    String B = "b";
    String C = "c";
    String D = "d";
    String TITLE = "title";
    String TRUEOPTION = "trueoption";//正确选项(此处均为大写选项)

}
