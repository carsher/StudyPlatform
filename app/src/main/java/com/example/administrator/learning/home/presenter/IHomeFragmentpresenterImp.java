package com.example.administrator.learning.home.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;

/**
 * Created by Administrator on 2018/10/24 0024.
 */

public interface IHomeFragmentpresenterImp extends BasePresenter{
    String TYPE = "type";//课程类型
    String COURSENAME = "coursename";//课程名称
    String LEARNING = "learning";//学习该门课程的人数
    String BELONG = "belong";//该课程隶属于哪个教师(教师user,非教师名称)
    String SECITION ="seciton";//课程章节
    String IMGPATH = "imgpath";//该课程预览图片路径
    String ID = "id";//课程id

    void getCourse();
}
