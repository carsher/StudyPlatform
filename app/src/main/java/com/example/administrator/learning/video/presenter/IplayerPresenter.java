package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.video.common.bean.QlayEnter;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public interface IplayerPresenter extends BasePresenter{
    int NULL = 5;

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/20  14:45
     *  @方法说明：显示章节
     */
    void ShowSection();
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/20  14:45
     *  @方法说明：显示评论
     */
    void ShowCritic();
    /**
     *  @创建用户 somafish
     *  @创建时间 2018/10/20  14:45
     *  @方法说明：显示测试
     */
    void ShowTest();
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/20  14:45
     *  @方法说明：显示问答
     */
    void ShowQuestion();
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/23  10:04
     *  @方法说明：发送请求获取video资源
     */
    void repuestVideo(QlayEnter enter);

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/26  16:10
     *  @方法说明：发送请求获取video资源
     * @param user
     * @param videoid
     */



}
