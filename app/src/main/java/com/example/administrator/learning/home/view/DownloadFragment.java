package com.example.administrator.learning.home.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Path;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.utils.StaticCost;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.administrator.learning.video.common.bean.reList.sid;

/**
 * Created by Administrator on 2018/10/19 0019.
 */

public class DownloadFragment extends Fragment implements IDowmloadFragment {

    private View view;
    private GridView listView_down;
    private List<HashMap> list =null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_downloadrar, null);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        //http://127.0.0.1/api/getCourseList
        new GetData().execute(StaticCost.IP+"/api/getCourseList");
        listView_down.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(), DownloadDetailActivity.class);
                intent.putExtra("courseid",list.get(i).get("id")+"");
                startActivity(intent);
            }
        });
    }


    private void initUI() {
        listView_down = (GridView) view.findViewById(R.id.listview_downloadrar);
    }
    //http://127.0.0.1/api/getContentCourseA?courseid=5
    private List<HashMap> getData(String msg) {
        list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray obj = jsonObject.getJSONArray("computer");
            for (int i=0;i<obj.length();i++){
                JSONObject data = obj.getJSONObject(i);
                HashMap map = new HashMap();
                map.put("coursename",data.get("coursename"));
                map.put("imgpath",data.get("imgpath"));
                map.put("belong",data.get("belong"));
                map.put("id",data.get("id"));
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listView_down.setAdapter(new MyAdapter_record(list));
        return list;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //展示返回的数据

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
            View view = View.inflate(getActivity(),R.layout.item_downloadrar,null);
            TextView tv_1 = (TextView) view.findViewById(R.id.tv_1);//名称
            TextView tv_2 = (TextView) view.findViewById(R.id.tv_2);//名称
            ImageView img1 = (ImageView) view.findViewById(R.id.img1);
            ImageView img2 = (ImageView) view.findViewById(R.id.img2);
         //   if (position==0){
                StringBuilder sb = new StringBuilder();
                sb.append(StaticCost.IP).append(list.get(position).get("imgpath"));
                String path = sb.toString();
                Picasso.with(getActivity()).load(path).placeholder(R.drawable.loding_img).fit().into(img1);
                tv_1.setText(String.valueOf(list.get(position).get("coursename")));


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
            return DeatailScord.MyOkhttp.get(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                getData(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

