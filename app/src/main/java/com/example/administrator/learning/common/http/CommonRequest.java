package com.example.administrator.learning.common.http;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class CommonRequest {
    private HashMap<String,Object> body = null;
    private HashMap<String,String> header = new HashMap<>();
    private String url = null;
    private List<HashMap> list = new ArrayList<>();

    public void setBody(String key, Object object){
        body = new HashMap<>();
        body.put(key,object);
        list.add(body);
    }
    public void setHeader(String key, String values){
        header.put(key, values);
    }
    public HashMap<String,String> getHeader(){
        return header;
    }
    public List<HashMap> getBody(){
        return list;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return url;
    }
    public String getJson(){
        return new Gson().toJson(this.body,HashMap.class);
    }
}
