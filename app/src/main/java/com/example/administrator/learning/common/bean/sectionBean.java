package com.example.administrator.learning.common.bean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/23 0023.
 */

public class sectionBean {
    //所属章节id
    private String seril;
    //小节
    private String knobble;
    //视频时间
    private String videoTime;
    //标题
    private String title;
    //状态
    private String isread;
    //学习人数
    private String learnNumber;
    //视频URL
    private String videoURl;
    //课程ID
    private String courseID;
    //章节顺序
    private String sequence;
    //测试对应ID
    private String testid;
    private String smallsection;
    private String jsonArrlength;

    public String getJsonArrlength() {
        return jsonArrlength;
    }

    public void setJsonArrlength(String jsonArrlength) {
        this.jsonArrlength = jsonArrlength;
    }

    public String getSmallsection() {
        return smallsection;
    }

    public void setSmallsection(String smallsection) {
        this.smallsection = smallsection;
    }

    //使用List
    private List<HashMap> list;

    public List<HashMap> getList() {
        return list;
    }

    public void setList(List<HashMap> list) {
        this.list = list;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }

    public String getVideoURl() {
        return videoURl;
    }

    public void setVideoURl(String videoURl) {
        this.videoURl = videoURl;
    }

    public String getSeril() {
        return seril;
    }

    public void setSeril(String seril) {
        this.seril = seril;
    }

    public String getKnobble() {
        return knobble;
    }

    public void setKnobble(String knobble) {
        this.knobble = knobble;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        this.videoTime = videoTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getisread() {
        return isread;
    }

    public void setisread(String isread) {
        this.isread = isread;
    }

    public String getLearnNumber() {
        return learnNumber;
    }

    public void setLearnNumber(String learnNumber) {
        this.learnNumber = learnNumber;
    }
}
