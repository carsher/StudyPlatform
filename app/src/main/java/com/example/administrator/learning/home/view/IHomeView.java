package com.example.administrator.learning.home.view;

/**
 * Created by Administrator on 2018/10/18 0018.
 */

public interface IHomeView {

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/18  11:33
     *  @方法说明：根据index 具体决定显示某个fragment
     */
    void ShowFragment(int index);

    //void ShowLogin();
    void ShowSelectSchool();

    void close();

}
