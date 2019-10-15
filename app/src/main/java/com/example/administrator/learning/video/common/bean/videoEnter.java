package com.example.administrator.learning.video.common.bean;

import com.example.administrator.learning.common.bean.sectionBean;

import java.util.List;

/**
 * Created by Administrator on 2018/10/23 0023.
 */

public class videoEnter {
    private int state;
    private List<sectionBean> list;
    private String courseName;
    private int smallLength;
    private int JsonArrlength;

    public int getSmallLength() {
        return smallLength;
    }

    public void setSmallLength(int smallLength) {
        this.smallLength = smallLength;
    }

    public int getJsonArrlength() {
        return JsonArrlength;
    }

    public void setJsonArrlength(int jsonArrlength) {
        JsonArrlength = jsonArrlength;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public  List<sectionBean> getList() {
        return list;
    }

    public void setList(List<sectionBean> list) {
        this.list = list;
    }
}
