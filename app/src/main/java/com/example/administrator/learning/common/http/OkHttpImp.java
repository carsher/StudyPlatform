package com.example.administrator.learning.common.http;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class OkHttpImp implements IhttpClient{

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(HttpConfig.READ_OUT,TimeUnit.SECONDS)
            .build();
    private static OkHttpImp instance;


    private OkHttpImp(){}
    public static synchronized OkHttpImp getInstance(){
        if (instance == null){
            instance = new OkHttpImp();
        }
        return instance;
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  10:14
     *  @方法说明：具体实现http请求
     */
    @Override
    public CommonRespones get(CommonRequest request) {
        String url = request.getUrl();
        HashMap<String,String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();

        for (String key: header.keySet()
                ) {
            builder.header(key,header.get(key));
        }
        builder.url(url);
        Request okrequest = builder.build();
        return execute(okrequest);
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/16  10:14
     *  @方法说明：发送post请求，请求参数以json格式发送
     */
    @Override
    public CommonRespones post(CommonRequest request) {
        //定义数据格式为 json  格式
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String body = request.getJson();
        //RequestBody bodys = RequestBody.create(mediaType,body);
        FormBody.Builder requestBodyPost = new FormBody.Builder();

        for (HashMap<String,String> map:request.getBody()){
            for (String key:map.keySet()
                    ) {
//                Log.e("ceshi:", "key:" + key + "data:" + map.get(key));
                requestBodyPost.add(key.toString(),map.get(key.toString()));
            }
        }


        HashMap<String,String> header = request.getHeader();
        Request.Builder builder = new Request.Builder();
        for (String key: header.keySet()
                ) {
            builder.header(key,header.get(key));
        }

        Request okrequest =  builder.url(request.getUrl()).post(requestBodyPost.build()).build();

        return execute(okrequest);
    }



    public CommonRespones upFile(File file, String url){
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if(file != null){
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", filename, body).addFormDataPart("user", url);
        }

        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        CommonRespones commonRespones = execute(request);
        return commonRespones;
    }


    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/15  10:14
     *  @方法说明：封装返回
     */
    private CommonRespones execute(Request request){
        CommonRespones commresponse = new CommonRespones();
        try {
            Response response = client.newCall(request).execute();
            commresponse.setBody(response.body().string().replace("\uFEFF",""));
            commresponse.setState_code(response.code());
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常
            commresponse.setState_code(CommonRespones.STATE_EEROER_CODE);
            commresponse.setBody(e.getMessage()+"网络请求超时");
        }
        return commresponse;
    }
}
