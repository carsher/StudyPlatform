package com.example.administrator.learning.common.http;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class HttpConfig {
    //测试阶段使用的域名
    public static final String DOMAIN_TEST = "";
    //项目真实阶段使用的域名
    public static final String DOMAIN_PRO = "";
    public boolean DEBUG = true;
    public static final long TIME_OUT = 4;  //请求超时
    public static final long READ_OUT = 4;  //读取超时


    /**
     *  @创建用户 somafish
     *  @创建时间 2018/3/11  10:50
     *  @方法说明：获取到当前域名
     */
    public String getDomain(){
        if (DEBUG){
            return DOMAIN_TEST;
        }else{
            return  DOMAIN_PRO;
        }
    }
}
