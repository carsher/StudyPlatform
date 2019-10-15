package com.example.administrator.learning.video.common.bean;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class setCriticRequest {
    //课程ID
    private String courseID;
    //小节ID
    private String sectionID;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }
}
