package com.example.administrator.learning.video.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.view.ProgressDialogByMyself;
import com.example.administrator.learning.video.common.bean.onPostEntity;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.AddPostPresenter;
import com.example.administrator.learning.video.presenter.IAddPostPresenter;
import com.example.administrator.learning.video.presenter.IPostPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPostActivity extends AppCompatActivity implements IAddPostActivity{

    private Button btn_addpost;
    private TextView edt_content;
    private IAddPostPresenter presenter;
    private String user;
    private String pid="0";
    public ProgressDialogByMyself mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        if (presenter == null){
            presenter = new AddPostPresenter(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        mDialog = new ProgressDialogByMyself(this);
        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        pid = intent.getStringExtra("pid");
        Log.e("ADDPOSTACTIVITY","AddPostActivity: "+pid);
        initUI();
        initData();
    }

    private void initUI() {
        edt_content = (TextView) findViewById(R.id.edt_content);
        btn_addpost = (Button) findViewById(R.id.add_Reviews);
        btn_addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
                String content = edt_content.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(System.currentTimeMillis());
                String dater = sdf.format(date);
                onPostEntity entity = new onPostEntity();
                entity.setUser(user);
                entity.setPid(pid);
                entity.setContent(content);
                entity.setTime(dater);
                presenter.geReply(entity);
            }
        });
    }

    private void initData() {


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.onDestroy();

    }

    @Override
    public void ShowData() {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(),"回复成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void ShowToash(String say) {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(),say,Toast.LENGTH_SHORT).show();
        finish();
    }
}
