package com.example.administrator.learning.main.model;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.example.administrator.learning.common.http.CommonRequest;
import com.example.administrator.learning.common.http.CommonRespones;
import com.example.administrator.learning.common.http.IhttpClient;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.rxbus.RxDao;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.main.common.AccountEntity;
import com.example.administrator.learning.main.common.ReigsterEntity;
import com.example.administrator.learning.main.common.SendCodeEntity;
import com.example.administrator.learning.main.presenter.IReigsterpresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import rx.functions.Func1;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class AccountMangerImp implements IAccountManger{
    private static IhttpClient OkhttpClient;
    public AccountMangerImp(IhttpClient client){
        this.OkhttpClient = client;
    }

    /**
     * @param account
     * @param pwd
     * @创建用户 somafish
     * @创建时间 2018/10/17  17:06
     * @方法说明：用户登录模块
     */
    @Override
    public void auth(final String account, final String pwd, final Context context) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object object) {
                //用户登录相关操作
                AccountEntity accout = new AccountEntity();
                CommonRequest request = new CommonRequest();
                String tokenencode = account+pwd;
                String token = Base64.encodeToString(tokenencode.getBytes(),Base64.DEFAULT);
                request.setUrl(StaticCost.DEMOAIN);
                Log.e("测试输出传值：", ""+ account + pwd +" mishi:" + token);
                request.setBody("user",account);
                request.setBody("pwd",pwd);
                CommonRespones respones = OkHttpImp.getInstance().post(request);
                String body =  respones.getBody();
                Log.e("服务器返回的数据：",body);
                try {
                    JSONObject jsonObject = new JSONObject(body);

                    String statu =  jsonObject.getString("isfind");
                    if(statu.equals("true")){
                        String type = jsonObject.getString("type");
                        if (type.equals("stu")) {
                            String isbind = jsonObject.getString("isbind");
                           if (!isbind.equals("2")) {
                                String id = jsonObject.getString("id");
                                String pwd = jsonObject.getString("pwd");
                                String addtime = jsonObject.getString("addtime");
                                String name = jsonObject.getString("name");
                                String classname = jsonObject.getString("classname");
                                SpUtils.putString(context, StaticCost.ID, id);
                                SpUtils.putString(context, StaticCost.PWD, pwd);
                                SpUtils.putString(context, StaticCost.ADDTIME, addtime);
                                SpUtils.putString(context,StaticCost.TYPE,type);
                               SpUtils.putString(context,StaticCost.ISBIND,isbind);
                               SpUtils.putString(context,StaticCost.NAME,name);
                               SpUtils.putString(context,StaticCost.CLASSNAME,classname);
                               Log.d("Accoutn", "call: " + Environment.getDataDirectory().getAbsolutePath());
                                SpUtils.putString(context, StaticCost.USER, account);
                                accout.setisbind(isbind);
                                accout.setState(200);
                                return accout;
                           } else {
                                //数据为零，需要跳转到选择学校页面
                                accout.setState(200);
                               accout.setisbind(isbind);
                                return accout;
                           }
                       } else{
                            accout.setState(202);
                            accout.setisbind("other");
                            return accout;
                        }
                    }else if(statu.equals("false")){
                        accout.setState(402);
                        return accout;
                    }

                } catch (Exception e) {
                        e.printStackTrace();
                        accout.setisbind("other");
                        accout.setState(201);
                        return accout;

                }

                accout.setState(201);  //帐号不存在
                return accout;
            }
        });
    }
    /**
     * @param account
     * @创建用户 crash
     * @创建时间 2018/10/16  17:05
     * @方法说明：用户注册模块
     */
    @Override
    public void reigster(final AccountEntity account) {
        //用户注册
        // TODO: 2018/10/19 验证码未完成
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object object) {
                ReigsterEntity entity = new ReigsterEntity();
                String phone = account.getAccount();
                String pwd = account.getPassword();
                String code = account.getCode();
                String type = account.getType();
                String number = account.getNumber();
                CommonRequest request = new CommonRequest();
                request.setBody("phone",phone);
                request.setBody("pwd",pwd);
                request.setBody("code",code);
                request.setBody("type",type);
                request.setBody("no",number);
                request.setUrl(StaticCost.REQUEST_REIGSTER);
                CommonRespones commonRespones =  OkhttpClient.post(request);
                String body = commonRespones.getBody();
                Log.e("注册返回的数据",""+ body);
                if(commonRespones.getState_code() == CommonRespones.STATE_SUC_CODE){
                    try {
                        JSONObject jsonObject = new JSONObject(body);
                       String state = jsonObject.getString("reigster_is_suc");
                        String errormessage = jsonObject.getString("error");
                        if(state.equals("error’")){
                            //注册失败
                            entity.setCode(IReigsterpresenter.USER_EXITS);
                            return entity;
                        }else if(state.equals("success")){
                            entity.setCode(IReigsterpresenter.USER_REIGSET_SUC);
                            return entity;
                        }else if(errormessage.equals("code_wring")){
                            entity.setCode(IReigsterpresenter.USER_CODEERROR);
                            return entity;
                        }else if (errormessage.equals("null")){
                            entity.setCode(IReigsterpresenter.USER_REIGSET_SUC);
                        }else if (errormessage.equals("noerror")){
                            entity.setCode(IReigsterpresenter.USER_NOERROR);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                entity.setCode(IReigsterpresenter.USER_NETWORD);
                return entity;
            }
        });

    }
    /**
     * @param account
     * @param context
     * @创建用户 crash
     * @创建时间 2018/10/17  19:04
     * @方法说明：选择学校后登陆 FFB985
     */
    @Override
    public void SelectSchool(final AccountEntity account, final Context context) {
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object object) {
                String name = account.getName();
                String no = account.getNo();
                CommonRequest request = new CommonRequest();
                request.setUrl(StaticCost.REQUEST_DEMOAIN_SELECTSCHOOL);
                request.setBody("user",name);
                request.setBody("no",no);
                CommonRespones commonRespones = OkhttpClient.post(request);
                Log.e("目前存储的user数据：" , SpUtils.getString(context,StaticCost.USER));
                    String body = commonRespones.getBody();
                Log.e("选择学校返回的数据",":"+body);

                try {
                        JSONObject jsonObject = new JSONObject(body);
                        String statu = jsonObject.getString("statu");
                        String errormessage = jsonObject.getString("error");
                        if (statu.equals("success")){
                                    SpUtils.putString(context,StaticCost.NAME,name);
                                    SpUtils.putString(context,StaticCost.NO,no);
                                    return "suc";
                        }else if (statu.equals("error")){
                            return "error";
                        }else if (errormessage.equals("notfind")){
                            return "notfind";
                        }else if (errormessage.equals("alreadbind")){
                            return "arlearreigster";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("异常","数据：" + e.getMessage());
                    }

                return "error";
            }
        });
    }

    @Override
    public void sendCode(final String phone) {
        //下发验证码  times
        RxDao.getInstance().ChainProcess(new Func1() {
            @Override
            public Object call(Object object) {
                SendCodeEntity entity = new SendCodeEntity();
                CommonRequest commonRequest = new CommonRequest();
                commonRequest.setUrl(StaticCost.REQUEST_SENDCODE);
                commonRequest.setBody("phone",phone);
                CommonRespones respones = OkhttpClient.post(commonRequest);
                Log.e("下发验证码", respones.getBody());
                if(respones.getState_code() == CommonRespones.STATE_SUC_CODE){
                    JSONObject jsonObject = null;
                    String statu="";
                    try {
                        jsonObject = new JSONObject(respones.getBody());
                         statu = jsonObject.getString("send_statu");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if(statu.equals("success")){
                        Log.e("下发验证码", "IReigsterpresenter.SEND_SUC");
                        entity.setCode(IReigsterpresenter.SEND_SUC);
                        return entity;
                    }else{
                        Log.e("下发验证码", "IReigsterpresenter.SEND_NETWORD");
                        entity.setCode(IReigsterpresenter.SEND_NETWORD);
                        return entity;
                    }
                }
                entity.setCode(IReigsterpresenter.SEND_NETWORD);
                return entity;
            }
        });
    }
}
//