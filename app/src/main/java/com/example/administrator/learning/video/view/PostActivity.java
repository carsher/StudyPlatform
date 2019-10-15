package com.example.administrator.learning.video.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.IPostPresenter;
import com.example.administrator.learning.video.presenter.IQuestionPresenter;
import com.example.administrator.learning.video.presenter.PostPresenter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 *  @创建用户 crash
 *  @创建时间 2018/11/03  18:52
 *  @方法说明：点击进去的帖子
 */
public class PostActivity extends AppCompatActivity implements IPostActivity,View.OnClickListener{

    private String id;
    private TextView tv_name;
    private TextView tv_title;
    private TextView tv_content;
    private String title;
    private String content;
    private String name;
    private ListView lv_post;
    private IPostPresenter presenter;
    private String time;
    private TextView tv_postTimer;
    private TextView add_post;
    private String pid="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent intent = getIntent();
        id = intent.getStringExtra(IQuestionPresenter.ID);
        Log.e("POSTACTIVITY","PostActivity: "+id);
        title = intent.getStringExtra(IQuestionPresenter.TITLE);
        content = intent.getStringExtra(IQuestionPresenter.CONTENT);
        name = intent.getStringExtra(IQuestionPresenter.USERS);
        time = intent.getStringExtra(IQuestionPresenter.TIME);

        if (presenter == null){
            presenter = new PostPresenter(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        presenter.getPostlist(id);
        initUI();
        initData();
    }

    private void initData() {
        tv_name.setText("提问人："+name);
        tv_content.setText(content);
        tv_title.setText(title);
        tv_postTimer.setText(time);
    }

    private void initUI() {
        tv_name = (TextView) findViewById(R.id.tv_questionName);
        tv_title = (TextView) findViewById(R.id.tv_title_question);
        tv_content = (TextView) findViewById(R.id.tv_content_question);
        tv_postTimer = (TextView) findViewById(R.id.tv_postTimer);
        lv_post = (ListView) findViewById(R.id.lv_post);
        add_post = (TextView) findViewById(R.id.add_post);

        add_post.setOnClickListener(this);

    }
//展示错误信息
    @Override
    public void ShowToash(String say) {
        Toast.makeText(getApplicationContext(),say+"",Toast.LENGTH_SHORT).show();
    }
//展示帖子
    @Override
    public void ShowData(List<HashMap> list) {
        lv_post.setAdapter(new MyAdapter_post(list));
        pid = id;
        Log.e("PID", "ShowData: "+pid+"ssss");

    }
//提问的点击事件
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(PostActivity.this,AddPostActivity.class);
        intent.putExtra("user",name);
        intent.putExtra("pid",pid);
        startActivity(intent);

    }

    private class MyAdapter_post extends BaseAdapter {
        public List<HashMap> list;
        public MyAdapter_post(List<HashMap> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getApplicationContext(),R.layout.item_post,null);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_postName);
            TextView tv_time = (TextView) view.findViewById(R.id.tv_postTime);
            TextView tv_content = (TextView) view.findViewById(R.id.tv_content_post);
            TextView tv_clickLike = (TextView) view.findViewById(R.id.tv_clickLike);
//            TextView tv_critic = (TextView) view.findViewById(R.id.tv_critic);

            HashMap map = list.get(position);
            tv_name.setText(map.get(IPostPresenter.USER)+"");
            tv_time.setText(map.get(IPostPresenter.TIME)+"");
            tv_content.setText(map.get(IPostPresenter.CONTENT)+"");
            tv_clickLike.setText(map.get(IPostPresenter.like)+"");
            return view;
        }
    }
}
