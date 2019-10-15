package com.example.administrator.learning.common.http;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class CommonRespones {
    private String body = null;
    private int state_code = 0;

    public static int STATE_EEROER_CODE = 400;
    public static int STATE_SUC_CODE = 200;

    public int getState_code() {
        return state_code;
    }

    public void setState_code(int state_code) {
        this.state_code = state_code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
