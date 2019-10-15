package com.example.administrator.learning.video.common;

import com.example.administrator.learning.common.bean.sectionBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/26 0026.
 */

public interface IFragmentInteraction {
    //sectionFragment传值给QlayActivity
    void process(String learnNum, String videoTime,String videoPath,String sectionID,String courseID,String title);
    //sectionFragnent传值给CriticFragment
}
