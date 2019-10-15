package com.example.administrator.learning.main.presenter;

import com.example.administrator.learning.common.utils.BasePresenter;

import java.io.File;

/**
 * Created by Administrator on 2018/10/17 0017.
 */

public interface ISelectSchoolpresnter extends BasePresenter{
    void selectSchool(String schoolname,String stu_num);

    String SCHOOLNAME = "shcool_name";
    String SCHOOLID = "id";
}
