package com.example.administrator.learning.video.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;
import com.example.administrator.learning.video.common.bean.DynamicEntity;

/**
 * Created by asdf on 2018/5/6.
 */

public interface IDynamicpresenter extends BasePresenter {

    void sendDynamicJustText(DynamicEntity entity);

    public static final String NAME = "name";
    public static final String ID = "id";



}
