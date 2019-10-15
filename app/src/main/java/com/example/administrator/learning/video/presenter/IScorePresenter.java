package com.example.administrator.learning.video.presenter;

import android.widget.BaseAdapter;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.video.common.bean.onscore;

/**
 * Created by 88888888 on 2018/11/18.
 */

public interface IScorePresenter extends BasePresenter{
    void regist(onscore score);

}
