package com.example.administrator.learning.home.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.utils.StaticCost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DeatailScord extends AppCompatActivity {
    private  ListView list_deatail_scored;
    private List<HashMap> list =null;
    private String sid = "1071";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail_scord);
        initUi();
        Intent intent =  getIntent();
        sid = intent.getStringExtra("sid");
        new GetData().execute(StaticCost.IP+"/api/getUserScordDetail?sid="+sid);

    }

    private void initUi() {
        list_deatail_scored  = (ListView) findViewById(R.id.list_deatail_scored);

    }


    private List<HashMap> getData(String msg) {
        list = new ArrayList<>();
        HashMap m = new HashMap();
        m.put("coursename","课程名");
        m.put("version","知识点");
        m.put("times","测试时长（秒）");
        m.put("score","测试成绩");
        m.put("no","序号");
        list.add(m);
        JSONArray obj = null;
        try {
            obj = new JSONArray(msg);
            for (int i=0;i<obj.length();i++){
                JSONObject data = obj.getJSONObject(i);
                Log.e("TAG",data.get("coursename").toString());
                HashMap map = new HashMap();
                DecimalFormat df   = new DecimalFormat("######0.00");
                map.put("coursename",data.get("coursename"));
                map.put("version",data.get("version"));
                map.put("times",data.get("times"));
                map.put("sid",data.get("sid"));
                map.put("score",df.format(Double.parseDouble(String.valueOf(data.get("score")))));
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        list_deatail_scored.setAdapter(new MyAdapter_record(list));
        return list;
    }

    private class MyAdapter_record extends BaseAdapter {
        private List<HashMap> list;
        public MyAdapter_record(List<HashMap> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(), R.layout.item_scored_record,null);
            TextView tb_name = (TextView) view.findViewById(R.id.tb_name);//名称
            TextView tb_course = (TextView) view.findViewById(R.id.tb_course);//时间
            TextView tb_smallsection = (TextView) view.findViewById(R.id.tb_smallsection);
            TextView tb_testNum = (TextView) view.findViewById(R.id.tb_testNum);
            TextView tb_testScord = (TextView) view.findViewById(R.id.tb_testScord);
            TextView tb_no = (TextView) view.findViewById(R.id.tb_no);
            if (position==0){
                view.findViewById(R.id.tb_row).setBackgroundResource(R.color.colorActionText);
                tb_no.setText("序号");
            }else{
                tb_no.setText(String.valueOf(position));
            }
            tb_course.setText(String.valueOf(list.get(position).get("coursename")));
            tb_smallsection.setText(String.valueOf(list.get(position).get("version")));
            tb_testNum.setText(String.valueOf(list.get(position).get("times")));
            tb_testScord.setText(String.valueOf(list.get(position).get("score")));
            return view;
        }
    }

    /**
     * 参数：url，progress，result
     */
    private class GetData extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {
            return MyOkhttp.get(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("TAG",s);
            getData(s);
        }
    }


    //对ok的简单设置
    static class MyOkhttp {
         static OkHttpClient client = new OkHttpClient();
         static String get(String url){
            try {
                client.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }

}
