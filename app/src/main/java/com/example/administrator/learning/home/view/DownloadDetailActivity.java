package com.example.administrator.learning.home.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.utils.StaticCost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadDetailActivity extends AppCompatActivity {
private String id = "5" ;
    private List<HashMap> list =null;
    private ListView list_downloadDeatail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_detail);
        initUI();
        Intent intent =  getIntent();
       id = intent.getStringExtra("courseid");
        new GetData().execute(StaticCost.IP+"/api/getContentCourseA?courseid="+id);

    }

    private void initUI() {
        list_downloadDeatail = (ListView) findViewById(R.id.list_downloadDetail);

    }

    private List<HashMap> getData(String msg) {
        list = new ArrayList<>();
        JSONArray obj = null;
        try {
            obj = new JSONArray(msg);
            for (int i=0;i<obj.length();i++){
                JSONArray jsonArray = obj.getJSONArray(i);
                for (int j=0;j<jsonArray.length();j++){
                    JSONObject data = jsonArray.getJSONObject(j);
                    HashMap map = new HashMap();
                    if (!data.get("txtsource").toString().equals("null")){
                        map.put("title",data.get("title"));
                        map.put("txtsource",data.get("txtsource"));
                        list.add(map);
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        list_downloadDeatail.setAdapter(new MyAdapter_record(list));
        return list;
    }
    private class MyAdapter_record extends BaseAdapter {
        private List<HashMap> list;
        public MyAdapter_record(List<HashMap> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            Log.e("TAQGLENTH",list.size()+"11111");
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
            View view = View.inflate(getApplicationContext(), R.layout.item_downdetail,null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            Button btn_down = (Button) view.findViewById(R.id.btn_down);
            tv_title.setText(String.valueOf(list.get(position).get("txtsource")));

            StringBuilder sb = new StringBuilder();
            sb.append(StaticCost.IP).append("/txtsource/").append(list.get(position).get("txtsource"));
            final String path = sb.toString();
            Log.e("TAGURL",path);
           // http://127.0.0.1/\txtsource/相对布局课堂资源.rar.
            btn_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openBrowser(getApplicationContext(),path);
                }
            });
            return view;
        }
    }
    /**
     * 调用第三方浏览器打开
     * @param context
     * @param url 要浏览的资源地址
     */
    public static  void openBrowser(Context context, String url){
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        // 注意此处的判断intent.resolveActivity()可以返回显示该Intent的Activity对应的组件名
        // 官方解释 : Name of the component implementing an activity that can display the intent
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            final ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            // 打印Log   ComponentName到底是什么
            Log.e("componentName = " , componentName.getClassName());
            context.startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else {
            Toast.makeText(context.getApplicationContext(), "请下载浏览器", Toast.LENGTH_SHORT).show();
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
            Log.e("TAGDEATAILoooo",s);
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
