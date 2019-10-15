package com.example.administrator.learning.video.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.home.view.HomeFragment;
import com.example.administrator.learning.video.common.bean.onscore;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.IScorePresenter;
import com.example.administrator.learning.video.presenter.Scorepresenter;


public class SecordActivity extends AppCompatActivity implements IScoreview{
    private String secord;
    private String courseId;
    private int sum;
    private String time;
    private String  coursename;
    private String user;
    private IScorePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secord);
        Intent intent = getIntent();
        sum = intent.getIntExtra("secord",0);
        coursename=intent.getStringExtra("coursename");
        courseId = intent.getStringExtra("courseID");
        time = intent.getStringExtra("time");
         user = SpUtils.getString(getApplicationContext(),StaticCost.USER);
        if (presenter == null){
            presenter = new Scorepresenter(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        initUI();
    }

    private void initUI() {
        TextView tv_secord = (TextView) findViewById(R.id.tv_secord);
        TextView tv_enter = (TextView) findViewById(R.id.tv_enter);
        TextView tv_time = (TextView) findViewById(R.id.tv_time);

        secord = sum*25>0?sum*25+"":"0";
        int timer=Integer.parseInt(time)/1000;
        int timers = timer/60;
        int timesss = timer%60;
        tv_time.setText(timers+"分"+ timesss +"秒");

        tv_secord.setText(secord);

        onscore score=new onscore();
        score.setScore(secord);
        score.setUser(user);
        score.setCourseid(courseId);
        score.setCoursename(coursename);
        score.setTimes(String.valueOf(timer));
        score.setName(SpUtils.getString(getApplicationContext(),StaticCost.NAME));
        score.setClassname(SpUtils.getString(getApplicationContext(),StaticCost.CLASSNAME));
        Log.e("FFFFFFFFFF","timer"+timer);
        presenter.regist(score);


        tv_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SecordActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter == null){
            presenter = new Scorepresenter(new VideoMangerImp(OkHttpImp.getInstance()),this);
        }
        presenter.unregister();
        presenter.onDestroy();
    }

    @Override
    public void say(String say) {
        Toast.makeText(getApplicationContext(),say,Toast.LENGTH_SHORT).show();
    }
}
