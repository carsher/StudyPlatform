package com.example.administrator.learning.video.common.bean;

/**
 * Created by Administrator on 2018/11/5 0005.
 */

public class onQuestionEntity {
    private String user;
    private String pid;
    private String content;
    private String cid;
    private String title;

    public String getCid() {

        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
}
