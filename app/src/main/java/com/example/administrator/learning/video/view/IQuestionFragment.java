package com.example.administrator.learning.video.view;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/11/3 0003.
 */

public interface IQuestionFragment {
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/3  14:47
     *  @方法说明：展示问答列表
     */
    void ShowData(List<HashMap> list);
    void ShowToash(String say);
}
