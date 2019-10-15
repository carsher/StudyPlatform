package com.example.administrator.learning.common.http;

import java.io.File;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public interface IhttpClient {
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  9:46
     *  @方法说明：发送get请求
     */

    CommonRespones get(CommonRequest request);
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  9:46
     *  @方法说明：发送post请请求
     */
    CommonRespones post(CommonRequest request);

    CommonRespones upFile(File file, String user);
}
