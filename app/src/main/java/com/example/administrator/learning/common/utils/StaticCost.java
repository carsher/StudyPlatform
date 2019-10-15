package com.example.administrator.learning.common.utils;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class StaticCost {
    public static final String USER="user";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String NO = "no";
    public static final String ISBIND= "isbind";
    public static final String TYPE = "type";
    public static final String PWD = "addtime";
    public static final String ADDTIME = "addtime";
    public static final String SCHOOLNAME = "schoolname";
    public static final String THESCHOOL = "theschool";
    public static final String VIDEOID = "videoid";
    //public static final String IP = "http://120.79.45.95";
  // public static final String IP = "http://192.168.43.146:80";
    public static final String IP = "http://10.0.2.2:80";
    public static final String CLASSNAME = "classname";

//http://120.79.45.95/api/sendCode
    public static final String CONFIG = "config";
    public static final String CID = "cid";
//注册
    public static final String REQUEST_REIGSTER = IP+"/api/reigster";
    //denglu
    public static final String DEMOAIN = IP+"/api/login";
    //发送验证码
    public static final String REQUEST_SENDCODE = IP+"/api/sendCode";

    public static final String REQUEST_DEMOAIN_SELECTSCHOOL = IP+"/api/bindStuInfo";  //测试地址
    //获取视频资源地址
    public static final String REQUEST_VIDEOID = IP+"/api/getContentCourse";
    //获取课程
    public static final String REQUEST_GETCOURSE = IP+"/api/getCourseList";
    //获取评论列表
    public static final String REQUEST_GETCRITIC = IP+"/api/pullDisscuz";
    //上传评论
    public static final String REQUEST_SEND_DYNAMIC = IP+"/api/sendMessage";
    //获取试题
    public static final String REQUEST_GETTEST = IP+"/api/getSubject";
    //获取问答列表
    public static final String REQUEST_GETQUESTIONLIST = IP+"/api/getInterlocutionList";
    //获取帖子列表
    public static final String REQUEST_GETPOSTLIST = IP+"/api/getInterlocutionDetile";
    //回复问题
    public static final String REQUEST_GETPOSREPLY = IP+"/api/replyQuestion";
    //发布问题
    public static final String REQUEST_GETQUESTIONREPLY = IP+"/api/putQuestion";

    //提交成绩
    public static final String POST_SCORE=IP+"/api/saveScore";
    public static final String POST_SCORE2=IP+"/api/recovderSmallItemTest";
    //标记某个用户查看了某一个小节的视频http://120.79.45.95/api/updateLearningInfo
    public static final String POST_VIDEOHISTORY=IP+"/api/updateLearningInfo";

}
