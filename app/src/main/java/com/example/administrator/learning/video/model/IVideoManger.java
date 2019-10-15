package com.example.administrator.learning.video.model;

import com.example.administrator.learning.video.common.bean.DynamicEntity;
import com.example.administrator.learning.video.common.bean.QlayEnter;
import com.example.administrator.learning.video.common.bean.TestEntity;
import com.example.administrator.learning.video.common.bean.onPostEntity;
import com.example.administrator.learning.video.common.bean.onQuestionEntity;
import com.example.administrator.learning.video.common.bean.onscore;

/**
 * Created by Administrator on 2018/10/23 0023.
 */

public interface IVideoManger {
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/23  10:26
     *  @方法说明：获取视频资源
     */
    void requestVideo(QlayEnter enter);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/28  15:00
     *  @方法说明：获取某条评论
     */
    void getContentDynamic(String courseID);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/29  11:50
     *  @方法说明：上传某条评论
     */
    void sendDynamicJust(DynamicEntity entity);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/30  9:34
     *  @方法说明：获取题目
     */
    void getTestWord(String testid);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/30  9:34
     *  @方法说明：获取问答列表
     */
    void getQuestionList(String courseid);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  19:22
     *  @方法说明：获取帖子列表
     */
    void getPostlist(String id);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  19:22
     *  @方法说明：回复问题
     */
    void getReply(onPostEntity entity);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  19:22
     *  @方法说明 发布问题
     */
    void getReplyQuestion(onQuestionEntity entity);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  19:22
     *  @方法说明 提交分数
     */
    void regist(onscore score);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  19:22
     *  @方法说明 标记某个用户查看了某一个小节的视频
     */
    void sendVideoHistory(String sid,String name,String user,String classname);
}
