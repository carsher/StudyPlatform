package com.example.administrator.learning.video.view;

import com.example.administrator.learning.video.common.bean.onCriticEntity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/28 0028.
 */

public interface ICriticFragment {

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/28  14:45
     *  @方法说明：展示评论
     */
    void ShowContentDynamic(List<HashMap> list);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/28  14:44
     *  @方法说明：展示错误
     */
    void ShowToash(String say);
}
