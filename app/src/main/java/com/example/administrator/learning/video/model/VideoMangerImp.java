package com.example.administrator.learning.video.model;

import android.util.Log;

import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.common.http.CommonRequest;
import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.http.IhttpClient;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.video.common.bean.DynamicEntity;
import com.example.administrator.learning.video.common.bean.PostEntiy;
import com.example.administrator.learning.video.common.bean.QlayEnter;
import com.example.administrator.learning.video.common.bean.QuestEntity;
import com.example.administrator.learning.video.common.bean.Scorerequset;
import com.example.administrator.learning.video.common.bean.TestEntity;
import com.example.administrator.learning.video.common.bean.onCriticEntity;
import com.example.administrator.learning.video.common.bean.onPostEntity;
import com.example.administrator.learning.video.common.bean.onQuestionEntity;
import com.example.administrator.learning.video.common.bean.onSendEntity;
import com.example.administrator.learning.video.common.bean.onTestState;
import com.example.administrator.learning.video.common.bean.onscore;
import com.example.administrator.learning.video.common.bean.videoEnter;
import com.example.administrator.learning.video.presenter.ICriticPresenter;
import com.example.administrator.learning.video.presenter.IPostPresenter;
import com.example.administrator.learning.video.presenter.IQuestionPresenter;
import com.example.administrator.learning.video.presenter.ITestPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Administrator on 2018/10/23 0023.
 */

public class VideoMangerImp implements IVideoManger{
    private static IhttpClient OkhttpClient;
    public VideoMangerImp(IhttpClient client){
        this.OkhttpClient = client;
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/23  10:32
     *  @方法说明：获取视频资源
     */
    @Override
    public void requestVideo(final QlayEnter enter) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                String videoID = enter.getVideoID();
                String useName = enter.getUseName();
                CommonRequest request = new CommonRequest();
                request.setBody("courseid",videoID);
                request.setBody("user",useName);
                request.setUrl(StaticCost.REQUEST_VIDEOID);
                videoEnter entity = new videoEnter();
                List<sectionBean> list = new ArrayList<>();
                CommonRespones respones =  OkhttpClient.post(request);
                String body = respones.getBody();
                Log.e("获取视频资源",""+ body);
                if(respones.getState_code() == CommonRespones.STATE_SUC_CODE){
                    try {
                        JSONObject jsonRoot= new JSONObject(body);
                        String courseName = jsonRoot.getString("coursename");
                        entity.setCourseName(courseName);
                        JSONArray jsonarr = jsonRoot.getJSONArray("section");
                        //json 数组 遍历
                        for (int i=0; i< jsonarr.length(); i++){
                            //获取到对应的数据  存入bean 中
                            JSONObject json = jsonarr.getJSONObject(i);
                            sectionBean bean = new sectionBean();
                            bean.setJsonArrlength(json.length()+"");
                            bean.setKnobble(json.getString("section_title"));
                            bean.setCourseID(json.getString("belong_course"));
                            bean.setLearnNumber(json.getString("learning"));
                            bean.setSequence(json.getString("section_sequence"));
                            String smallStr = json.getString("smallsction");
                            JSONArray smalljsonArr = null;
                            if(smallStr.equals("null")){
                                smalljsonArr =  new JSONArray();
                                bean.setSmallsection("0");
                            }else{
                                smalljsonArr = new JSONArray(smallStr);
                                bean.setSmallsection(smalljsonArr.length()+"");
                            }
                            List<HashMap> listmap = new ArrayList();
                            for (int j = 0;j<smalljsonArr.length();j++){
                                JSONObject jsonObjects = smalljsonArr.getJSONObject(j);
                                HashMap map = new HashMap();
                                Log.e("小节",jsonObjects.getString("title"));
                                Log.e("out",jsonObjects.toString());
                                map.put("id",jsonObjects.getString("id"));
                                map.put("viedo_length",jsonObjects.getString("viedo_length"));
                                map.put("videopath",jsonObjects.getString("videopath"));
                                map.put("title",jsonObjects.getString("title"));
                                map.put("testid",jsonObjects.getString("testid"));
                                map.put("isread",jsonObjects.getString("isread"));
                                map.put("sid",jsonObjects.getString("sid"));
                                Log.e("小节标题", "call: "+map.get("title"));
                                listmap.add(map);
                            }
                            bean.setList(listmap);
                            list.add(bean);
                        }
                        entity.setJsonArrlength(jsonarr.length());
                       // entity.setSmallLength(7);
                        entity.setList(list);
                        Log.e("判断有没有走", "锤死你ssssssssss");
                        entity.setState(CommonRespones.STATE_SUC_CODE);
                    } catch (JSONException e) {
                        Log.e("err",e.getMessage()+"aaaaaaaaa");
                        entity.setState(CommonRespones.STATE_EEROER_CODE);
                    }

                }else {
                    entity.setState(CommonRespones.STATE_EEROER_CODE);
                }

                return entity;
            }
        });
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/28  15:15
     *  @方法说明：获取某条评论
     */
    @Override
    public void getContentDynamic(final String courseID) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                List<HashMap> list = new ArrayList<HashMap>();
                onCriticEntity entity = new onCriticEntity();
                CommonRequest request = new CommonRequest();
                request.setBody("id",104+"");
                Log.e("getContentDynamic","----------"+courseID);
                request.setUrl(StaticCost.REQUEST_GETCRITIC);
                CommonRespones respones = OkHttpImp.getInstance().post(request);
                String body = respones.getBody();
                Log.e("获取评论返回的数据", "critic: "+body);
                if(respones.getState_code() == CommonRespones.STATE_SUC_CODE){
                    try {
                        JSONArray jsonArray = null;
                        if (respones.getBody().equals("null")){
                            jsonArray = new JSONArray();
                        }else{
                            jsonArray = new JSONArray(body);
                        }
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonobj = jsonArray.getJSONObject(i);
                            HashMap map = new HashMap();
                            map.put(ICriticPresenter.ID,jsonobj.getString(ICriticPresenter.ID));
                            map.put(ICriticPresenter.PID,jsonobj.getString(ICriticPresenter.PID));
                            map.put(ICriticPresenter.USER,jsonobj.getString(ICriticPresenter.USER));
                            map.put(ICriticPresenter.TIME,jsonobj.getString(ICriticPresenter.TIME));
                            map.put(ICriticPresenter.SAY,jsonobj.getString(ICriticPresenter.SAY));
                            list.add(map);
                        }
                        entity.setState(200);
                        entity.setList(list);
                    } catch (JSONException e) {
                        entity.setState(400);
                       // e.printStackTrace();
                        Log.e("ssssssss", "call: "+e.getMessage()+"hhhhhhhhhhhh");
                    }
                }else{
                    entity.setState(400);
                }
                return entity;
            }
        });
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/29  11:52
     *  @方法说明：上传某条评论
     */
    @Override
    public void sendDynamicJust(final DynamicEntity entity) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object object) {
                onSendEntity sendEntity = new onSendEntity();
                CommonRequest request = new CommonRequest();
                    request.setBody("pid",entity.getCourseId());
                    request.setBody("user",System.currentTimeMillis()+"");
                    request.setBody("say",entity.getContent());
                    request.setUrl(StaticCost.REQUEST_SEND_DYNAMIC);
                    //发起网络请求
                    CommonRespones respones = OkHttpImp.getInstance().post(request);
                if(respones.getState_code() == CommonRespones.STATE_SUC_CODE && Integer.parseInt(respones.getBody())>0){
                        Log.e("本地服务器存储", " 上传到本地服务器成功" + respones.getBody());
                        sendEntity.setStatu(CommonRespones.STATE_SUC_CODE);
                        sendEntity.setRequestId(respones.getBody());
                    } else{
                    Log.e("评论数据上上传失败" , "上传失败了");
                    sendEntity.setStatu(CommonRespones.STATE_EEROER_CODE);
                }
                return sendEntity;
            }
        });
    }

/**
 *  @创建用户 crash
 *  @创建时间 2018/10/29  11:52
 *  @方法说明：获取测试题目
 */
    @Override
    public void getTestWord(final String testid) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                onTestState state = new onTestState();
                CommonRequest request = new CommonRequest();
                request.setBody("id",testid);
                request.setUrl(StaticCost.REQUEST_GETTEST);
                CommonRespones respones = OkHttpImp.getInstance().post(request);
                String body = respones.getBody();
                Log.e("测试的题目", "body: "+body);
                List list = new ArrayList();
                if (respones.getState_code() == CommonRespones.STATE_SUC_CODE){
                    try {
                        JSONArray jsonArray = new JSONArray(body);;
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject json = jsonArray.getJSONObject(i);
                            HashMap map = new HashMap();
                            map.put(ITestPresenter.TESTID,json.getString(ITestPresenter.TESTID));
                            map.put(ITestPresenter.TITLE,json.getString(ITestPresenter.TITLE));
                            map.put(ITestPresenter.TRUEOPTION,json.getString(ITestPresenter.TRUEOPTION));
                            map.put(ITestPresenter.PID,json.getString(ITestPresenter.PID));
                            map.put(ITestPresenter.A,json.getString(ITestPresenter.A));
                            map.put(ITestPresenter.B,json.getString(ITestPresenter.B));
                            map.put(ITestPresenter.C,json.getString(ITestPresenter.C));
                            map.put(ITestPresenter.D,json.getString(ITestPresenter.D));

                            list.add(map);
                        }
                        state.setList(list);
                        state.setState(200);
                    } catch (JSONException e) {
                        state.setState(400);
                        e.printStackTrace();}
                }else{
                    state.setState(400);
                }
                return state;
            }
        });
    }

/**
 *  @创建用户 crash
 *  @创建时间 2018/10/29  11:52
 *  @方法说明：获取问答列表
 */
    @Override
    public void getQuestionList(final String courseid) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                List<HashMap> list = new ArrayList<HashMap>();
                QuestEntity entity = new QuestEntity();
                CommonRequest request = new CommonRequest();
                request.setBody("id",courseid);
                Log.e("VIDEOMANGERIMP","VideoMangerImp"+courseid);
                request.setUrl(StaticCost.REQUEST_GETQUESTIONLIST);
                CommonRespones respones = OkHttpImp.getInstance().post(request);
                String body = respones.getBody();
                Log.e("GETQUESTIONLIST", "getQuestionList: "+body);
                if (respones.getState_code() == CommonRespones.STATE_SUC_CODE){
                    try {
                        JSONArray jsonArray = null;
                        //TODO kn为空
                        if(respones.getBody().equals("null")){
                            jsonArray =  new JSONArray();
                        }else{
                            jsonArray = new JSONArray(body);
                        }
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject json = jsonArray.getJSONObject(i);
                            HashMap map = new HashMap();
                            map.put(IQuestionPresenter.ID,json.getString(IQuestionPresenter.ID));
                            map.put(IQuestionPresenter.TITLE,json.getString(IQuestionPresenter.TITLE));
                            map.put(IQuestionPresenter.CONTENT,json.getString(IQuestionPresenter.CONTENT));
                            map.put(IQuestionPresenter.TIME,json.getString(IQuestionPresenter.TIME));
                            map.put(IQuestionPresenter.USERS,json.getString(IQuestionPresenter.USERS));
                            Log.e("GETQUESTIONLIST", "call: "+map.get(IQuestionPresenter.TITLE)+"---");
                            list.add(map);
                        }
                        entity.setState(200);
                        entity.setList(list);
                    } catch (JSONException e) {
                        entity.setState(400);
                        e.printStackTrace();
                    }
                }else{
                    entity.setState(400);
                }

                return entity;
            }
        });
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/03  19:32
     *  @方法说明：获取列表
     */
    @Override
    public void getPostlist(final String id) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                List<HashMap> list = new ArrayList();
                PostEntiy entiy = new PostEntiy();
                CommonRequest request = new CommonRequest();
                request.setBody("id",id);
                request.setUrl(StaticCost.REQUEST_GETPOSTLIST);
                CommonRespones respones = OkHttpImp.getInstance().post(request);
                String body = respones.getBody();
                Log.e("getPostlist", "call: "+body);
                if (respones.getState_code() == CommonRespones.STATE_SUC_CODE){
                    try {
                        JSONObject json = new JSONObject(body);
                        String jsarr = json.getString("list");
                        JSONArray listjsonArr = null;
                        if(jsarr.equals("null")){
                            listjsonArr =  new JSONArray();
                        }else{
                            listjsonArr = new JSONArray(jsarr);
                        }

                        for (int i = 0;i<listjsonArr.length();i++){
                            JSONObject jsonObjects = listjsonArr.getJSONObject(i);
                            HashMap map = new HashMap();
                            map.put("id",jsonObjects.getString("id"));
                            map.put("content",jsonObjects.getString("content"));
                            map.put("like",jsonObjects.getString("like"));
                            map.put("time",jsonObjects.getString("time"));
                            map.put("pid",jsonObjects.getString("pid"));
                            map.put("user",jsonObjects.getString("user"));
                            list.add(map);
                        }
                        entiy.setState(200);
                        entiy.setList(list);
                    } catch (JSONException e) {
                        entiy.setState(200);
                        e.printStackTrace();
                    }

                }else{
                    entiy.setState(200);
                }
                return entiy;
            }
        });
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/03  19:32
     *  @方法说明：回复问题
     */
    @Override
    public void getReply(final onPostEntity entity) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                String state ;
                CommonRequest request = new CommonRequest();
                request.setBody("user",entity.getUser()+"");
                request.setBody("pid",entity.getPid()+"");
                request.setBody("content",entity.getContent()+"");
                Log.e("GGGGG","FFFF"+entity.getContent());
                Log.e("GGGGG","FFFF"+entity.getPid());
                request.setBody("time",entity.getTime()+"");
                request.setUrl(StaticCost.REQUEST_GETPOSREPLY);
                CommonRespones respones = OkHttpImp.getInstance().post(request);
                String body = respones.getBody();
                Log.e("VIEDOMANGERIMP", "call: "+body);
                try {
                    JSONObject jsons = new JSONObject(body);
                     state = jsons.getString("statu");
                     if (state.equals("success")){
                         Log.e("VIEDOMANGERIMP", "回复成功了");
                     }
                } catch (JSONException e) {
                    state = "falsh";
                    e.printStackTrace();
                }

                return state;
            }
        });
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/05  19:32
     *  @方法说明：发布问题
     */
    @Override
    public void getReplyQuestion(final onQuestionEntity entity) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                String state = "success";
                CommonRequest request = new CommonRequest();
                request.setBody("user",entity.getUser()+"");
                request.setBody("content",entity.getContent()+"");
                request.setBody("time",entity.getTime()+"");
                request.setBody("cid",entity.getCid()+"");
                request.setBody("title",entity.getTitle()+"");
                Log.e("content",entity.getContent());
                Log.e("title",entity.getTitle());
                Log.e("title",entity.getTime());
                request.setUrl(StaticCost.REQUEST_GETQUESTIONREPLY);
                CommonRespones respones = OkHttpImp.getInstance().post(request);
                String body = respones.getBody();
                Log.e("VIEDOMANGERIMP", "call: "+body);
                try {
                    JSONObject jsons = new JSONObject(body);
                    state = jsons.getString("statu");
                } catch (JSONException e) {
                    state = "falsh";
                    e.printStackTrace();
                }

                return state;
            }
        });
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/05  19:32
     *  @方法说明：上传成绩
     */
    @Override
    public void regist(final onscore score) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                Scorerequset resquet=new Scorerequset();
                String score1=score.getScore();
                String user=score.getUser();
                String sid=score.getCourseid();
                String coursename=score.getCoursename();

                CommonRequest request=new CommonRequest();
                request.setBody("score",score1+"");
                request.setBody("user",user+"");
                request.setBody("sid",sid+"");
              //  request.setBody("coursename",coursename+"");
                request.setBody("during",score.getTimes()+"");
                request.setBody("name",score.getName()+"");
                request.setBody("classname",score.getClassname()+"");
                request.setUrl(StaticCost.POST_SCORE2);
                CommonRespones commonRespones=OkhttpClient.post(request);
                String body=commonRespones.getBody();
                Log.e("SCOREMANGER", "call: "+body);
                String state;
                try {
                    JSONObject jsons = new JSONObject(body);
                    state = jsons.getString("statu");
                    if (state.equals("success")){
                        System.out.println("--------------请求成功");
                        resquet.setScod(200);
                    }else{
                        System.out.println("--------------请求失败");
                        resquet.setScod(400);
                    }
                } catch (JSONException e) {
                    System.out.println("--------------请求失败-----");
                    e.printStackTrace();
                }
                resquet.setScod(400);
                return resquet;
            }
        });

    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/05  19:32
     *  @方法说明： //标记某个用户查看了某一个小节的视频
     */
    @Override
    public void sendVideoHistory(final String sid, final String name, final String user, final String classname) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object o) {
                CommonRequest request=new CommonRequest();
                request.setBody("sid",sid+"");
                request.setBody("user",name+"");
                request.setBody("name",user+"");
                request.setBody("classname",classname+"");
                request.setUrl(StaticCost.POST_VIDEOHISTORY);
                CommonRespones commonRespones=OkhttpClient.post(request);
                String body=commonRespones.getBody();
                Log.e("SENDVIDEOHISTORY", "call: "+body);
                String state;
                try {
                    JSONObject jsons = new JSONObject(body);
                    state = jsons.getString("statu");
                    if (state.equals("success")){
                        Log.e("VIDEOMANGERIMP","上传用户查看了某一个小节的视频成功");
                    }else{
                        Log.e("VIDEOMANGERIMP","上传用户查看了某一个小节的视频失败"+"sid"+sid+"name"+name+"user"+user);
                    }
                } catch (JSONException e) {
                    state = "falsh";
                    Log.e("VIDEOMANGERIMP","上传用户查看了某一个小节的视频失败222");
                    e.printStackTrace();
                }
                return state;
            }
        });

    }
}
