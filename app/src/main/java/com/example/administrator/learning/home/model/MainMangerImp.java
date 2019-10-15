package com.example.administrator.learning.home.model;

import android.content.Context;
import android.util.Log;

import com.example.administrator.learning.common.http.CommonRequest;
import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.http.IhttpClient;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.home.common.homeEntey;
import com.example.administrator.learning.home.presenter.IHomeFragmentpresenterImp;
import com.example.administrator.learning.home.view.HomeFragment;
import com.example.administrator.learning.main.common.AccountEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import rx.functions.Func1;

/**
 * Created by Administrator on 2018/10/19 0019.
 */

public class MainMangerImp implements IMainManger {
    private static IhttpClient OkhttpClient;

    public MainMangerImp(IhttpClient client) {
        this.OkhttpClient = client;
    }


    /**
     * @创建用户 crash
     * @创建时间 2018/10/19  11:00
     * @方法说明：检查用户是否登录
     */
    @Override
    public void IsLogin(final Context context) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object object) {
                AccountEntity account = new AccountEntity();
                if (SpUtils.getString(context, StaticCost.USER).equals("null")) {
                    //还未登录，跳到登录页面
                    account.setState(400);
                    Log.e("返回数据", SpUtils.getString(context, StaticCost.USER) + " ");
                    return account;
                }
                Log.e("测试输出姓名", SpUtils.getString(context, StaticCost.USER) + "");
                //TODO
                if (SpUtils.getString(context, StaticCost.THESCHOOL).equals("0")) {
                    //未注册完成  跳到 选择学校的页面
                    account.setState(401);
                    return account;
                }

                account.setState(200);
                return account;

            }
        });
    }
    /**
     * @创建用户 crash
     * @创建时间 2018/10/19  11:00
     * @方法说明：获取课程
     */
    @Override
    public void getCourse() {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object object) {
                homeEntey myEnter = new homeEntey();
                CommonRequest request = new CommonRequest();
                request.setUrl(StaticCost.REQUEST_GETCOURSE);
                CommonRespones respones = OkhttpClient.post(request);
                String body = respones.getBody();
//                body = "{\"computer\":[{\"0\":\"1\",\"id\":\"1\",\"1\":\"Java从入门到精通\",\"coursename\":\"Java从入门到精通\",\"2\":\"185\",\"learning\":\"185\",\"3\":\"15015484386\",\"belong\":\"15015484386\",\"4\":\"计算机\",\"type\":\"计算机\",\"5\":\"20\",\"seciton\":\"20\",\"6\":\"\\/courseresource\\/xxxxx.jpg\",\"imgpath\":\"\\/courseresource\\/xxxxx.jpg\"},{\"0\":\"2\",\"id\":\"2\",\"1\":\"PHP从入门到精通\",\"coursename\":\"PHP从入门到精通\",\"2\":\"999\",\"learning\":\"999\",\"3\":\"15015484386\",\"belong\":\"15015484386\",\"4\":\"计算机\",\"type\":\"计算机\",\"5\":\"20\",\"seciton\":\"20\",\"6\":\"\\/courseresource\\/xxxx1111x.jpg\",\"imgpath\":\"\\/courseresource\\/xxxx1111x.jpg\"}],\"history\":[{\"0\":\"3\",\"id\":\"3\",\"1\":\"中国上下五千年\",\"coursename\":\"中国上下五千年\",\"2\":\"187\",\"learning\":\"187\",\"3\":\"18219265976\",\"belong\":\"18219265976\",\"4\":\"文史类\",\"type\":\"文史类\",\"5\":\"30\",\"seciton\":\"30\",\"6\":\"\\/courseresource\\/0xxxx1xxxx.jpg\",\"imgpath\":\"\\/courseresource\\/0xxxx1xxxx.jpg\"},{\"0\":\"4\",\"id\":\"4\",\"1\":\"史记\",\"coursename\":\"史记\",\"2\":\"588\",\"learning\":\"588\",\"3\":\"18219265976\",\"belong\":\"18219265976\",\"4\":\"文史类\",\"type\":\"文史类\",\"5\":\"18\",\"seciton\":\"18\",\"6\":\"\\/courseresource\\/x111xxxxx.jpg\",\"imgpath\":\"\\/courseresource\\/x111xxxxx.jpg\"}]}";
                Log.e("MAINMANGERIMP", "" + body);
                if (respones.getState_code() == CommonRespones.STATE_SUC_CODE) {
                    try {
                        JSONObject jsonRoot = new JSONObject(body);
//                        JSONArray jsonArray = jsonRoot.getJSONArray("computer");
                        ArrayList list = new ArrayList();
                        JSONArray jsonArray = null;
                        if (respones.getBody().equals("null")){
                            jsonArray = new JSONArray();
                        }else{
                            jsonArray = jsonRoot.getJSONArray("computer");
                        }

                        /*解析数据*/
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            HashMap map = new HashMap();
                            map.put(IHomeFragmentpresenterImp.TYPE, jsonObject.getString("type"));
                            map.put(IHomeFragmentpresenterImp.BELONG, jsonObject.getString("belong"));
                            map.put(IHomeFragmentpresenterImp.COURSENAME, jsonObject.getString("coursename"));
                            map.put(IHomeFragmentpresenterImp.SECITION, jsonObject.getString("belong"));
                            map.put(IHomeFragmentpresenterImp.IMGPATH, jsonObject.getString("imgpath"));
                            map.put(IHomeFragmentpresenterImp.LEARNING, jsonObject.getString("learning"));
                            map.put(IHomeFragmentpresenterImp.ID, jsonObject.getString("id"));
                            list.add(map);
                        }
                        Log.e("MAINMANGERIMP","课程存入list中了"+list.size());
                        myEnter.setList(list);
                        myEnter.setStatu(CommonRespones.STATE_SUC_CODE);


                        ArrayList listhistory = new ArrayList();
                        JSONArray jsonArrayhistory = null;
                        if (jsonRoot.getString("history").equals("null")){
                            jsonArrayhistory = new JSONArray();
                        }else{
                            jsonArrayhistory = jsonRoot.getJSONArray("history");
                        }

                         /*解析数据*/
                        for (int i = 0; i < jsonArrayhistory.length(); i++) {
                            JSONObject jsonObject = jsonArrayhistory.getJSONObject(i);
                            HashMap map = new HashMap();
                            map.put(IHomeFragmentpresenterImp.TYPE, jsonObject.getString("type"));
                            map.put(IHomeFragmentpresenterImp.BELONG, jsonObject.getString("belong"));
                            map.put(IHomeFragmentpresenterImp.COURSENAME, jsonObject.getString("coursename"));
                            map.put(IHomeFragmentpresenterImp.SECITION, jsonObject.getString("belong"));
                            map.put(IHomeFragmentpresenterImp.IMGPATH, jsonObject.getString("imgpath"));
                            map.put(IHomeFragmentpresenterImp.LEARNING, jsonObject.getString("learning"));
                            map.put(IHomeFragmentpresenterImp.ID, jsonObject.getString("id"));
                            listhistory.add(map);
                        }
                        myEnter.setListHistory(listhistory);
                        myEnter.setStatu(CommonRespones.STATE_SUC_CODE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        myEnter.setStatu(CommonRespones.STATE_EEROER_CODE);
                    }
                }else{
                    myEnter.setStatu(CommonRespones.STATE_EEROER_CODE);
                }
                return myEnter;
            }
        });

    }
}
