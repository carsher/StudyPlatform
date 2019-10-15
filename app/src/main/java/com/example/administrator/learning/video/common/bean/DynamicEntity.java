package com.example.administrator.learning.video.common.bean;

import com.example.administrator.learning.home.view.UserFragment;

/**
 * Created by Administrator on 2018/10/29 0029.
 */

public class DynamicEntity {
    private String DYnamicContent;
    private String courseId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
    private String user;
    private String time;

    public String getDYnamicContent() {
        return DYnamicContent;
    }

    public void setDYnamicContent(String DYnamicContent) {
        this.DYnamicContent = DYnamicContent;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
