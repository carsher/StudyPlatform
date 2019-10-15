package com.example.administrator.learning.home.view;

import com.example.administrator.learning.home.common.homeEntey;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/24 0024.
 */

public interface IHomeFragment {
    void ShowData(homeEntey entiy);
    //没有课程时显示
    void ShowNoData();
    void ShowToast(String say);
}
