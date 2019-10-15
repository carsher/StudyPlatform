package com.example.administrator.learning.main.common;

/**
 * Created by Administrator on 2018/10/16 0016.
 * 用户模型
 */

public class AccountEntity {
    public String account;
    public String password;
    public int state;
    public int run_count;
    public int ride_count;
    public String type;
    public int cilm_cout;
    public String isbind;
    public String name;
    private String no;
    private String Number;//学号

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String cid;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String code;

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String face;

    public String getStd_num() {
        return std_num;
    }

    public void setStd_num(String std_num) {
        this.std_num = std_num;
    }

    public String std_num;

    public int getCilm_cout() {
        return cilm_cout;
    }

    public void setCilm_cout(int cilm_cout) {
        this.cilm_cout = cilm_cout;
    }

    public int getRun_count() {
        return run_count;
    }

    public void setRun_count(int run_count) {
        this.run_count = run_count;
    }

    public int getRide_count() {
        return ride_count;
    }

    public void setRide_count(int ride_count) {
        this.ride_count = ride_count;
    }

    public String getisbind() {
        return isbind;
    }

    public void setisbind(String isbind) {
        this.isbind = isbind;
    }




    //未登录
    public static final int STATE_USER_NOT_LOGIN = 0;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
