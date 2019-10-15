package com.example.administrator.learning.video.common.bean;

/**
 * Created by Administrator on 2018/11/5 0005.
 */

public interface reList {
     static String section_title="section_title";//章节标题
     static String belong_course="belong_course";//所属课程id
     static String id="id";//小节id
     String section_sequence="section_sequence";//章节顺序(注意返回不一定是完全按照1,2,3这样的小节排序，准确顺序应该按照该节点排序)
     String learning="learning";//该小节学习人数
     String sid="sid";//所属章节id
     String videopath="videopath";//小节对应的视频路径 例如：/videopath/xxxxx.mp4
     String title="title";//小节对应的标题
     String testid="testid";//测试内容对应的id
     String isread="isread";//是否已经看过,read->已读，notread->未看过
     String viedo_length = "viedo_length";//视频长度

}
