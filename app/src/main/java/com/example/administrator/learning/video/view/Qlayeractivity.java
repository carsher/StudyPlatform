package com.example.administrator.learning.video.view;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.administrator.learning.R;

import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.common.database.DbAdapter;
import com.example.administrator.learning.common.http.OkHttpImp;

import com.example.administrator.learning.common.severs.DownLoadSevers;
import com.example.administrator.learning.common.utils.PixelUtils;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.video.common.IFragmentInteraction;
import com.example.administrator.learning.video.common.bean.QlayEnter;
import com.example.administrator.learning.video.common.bean.onsendTest;
import com.example.administrator.learning.video.common.bean.setCriticRequest;
import com.example.administrator.learning.video.common.bean.videoEnter;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.IplayerPresenter;
import com.example.administrator.learning.video.presenter.playerPresenterImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Qlayeractivity extends AppCompatActivity implements IQlayer_activity,View.OnClickListener,IFragmentInteraction {
    private SectionFragment sectionFragment = null;
    private CriticFragment criticFragment = null;
    private TestFragment testFragment = null;
    private QuestionFragment questionFragment = null;

    private ImageView img_section,img_critic,img_test,img_question,back_last1;
    private TextView tv_section,tv_critic,tv_test,tv_question;
    private IplayerPresenter presenter;
    private static FragmentTransaction fm;
    private android.support.v4.app.FragmentManager fram = getSupportFragmentManager();

    private ProgressBar progressBar;
    private MediaController mediaController;
    private int intPositionWhenPause ;//当前播发时间
    private IsectionFragment isectionfragment;
    private List<sectionBean> lister = new ArrayList<>();
    private String videoid;//课程ID
    private String user;//用户名
    private String courseName;//课程名称
    private TextView tv_courseName;
    private TextView tv_time;
    private TextView tv_learns;
    private String Videopather;//视频路径
    private DbAdapter dbAdapter;
    private String imgPath;//图片地址
    private String Timer;//时间
    private String dater;//系统时间
    private String titler;//小节标题
    private ImageView img_readygo;
    private ImageView img_hidd;

    private VideoView videoview;
    private ImageView play_controller_img,screen_img;
    private TextView current_text,time_total_tv;
    private SeekBar play_seek;
    private RelativeLayout relativeLayout;
    private boolean isFullScreen=false;
    private TextView tv_title_content;
    private TextView tv_downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        if(presenter == null){
            presenter = new playerPresenterImp(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        if (dbAdapter == null){
            dbAdapter = new DbAdapter(getApplicationContext());
            dbAdapter.open();
        }
        Intent intent =  getIntent();
        videoid = intent.getStringExtra("id");//课程id
        user = intent.getStringExtra("user");
        imgPath = intent.getStringExtra("imgpath")+"";
        Log.e("QLAYERACTIVITY","QLAYERACTIVITY----"+imgPath);
        initUI();
        ShowFragment(1);
        back_last1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Qlayeractivity.this.finish();
            }
        });
  //      setNavigationBarStatusBarTranslucent(this);
    }

    public void setNavigationBarStatusBarTranslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);

            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < 21 ) {
            Window window = activity.getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/13  15:01
     *  @方法说明 播发视频
     */
    public void playTheVideo(String url){
        setPlayEvent();
       // videoview.setVideoURI(Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
        videoview.setVideoURI(Uri.parse(url));
        videoview.start();
        progressBar.setVisibility(View.GONE);
        img_hidd.setVisibility(View.GONE);
        UIHandler.sendEmptyMessage(1);
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/20  16:20
     *  @方法说明 初始化videoview
     */
    @SuppressLint("DefaultLocale")
    private void  updataTextViewTime(TextView textView, int millsecond){
        int second = millsecond/1000;
        int hh = second/3600;
        int mm = second%3600/60;
        int ss = second%60;
        String str = null;
        if (hh!=0){
            str = String .format("%02d:%02d:%02d",hh,mm,ss);
        }else {
            str = String .format("%02d:%02d",mm,ss);

        }
        textView.setText(str);
    }
    @SuppressLint("HandlerLeak")
    private Handler UIHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                super.handleMessage(msg);
                int currentPosition = videoview.getCurrentPosition();
                intPositionWhenPause = currentPosition;
                int totalDuration = videoview.getDuration();
                updataTextViewTime(current_text,currentPosition);
                updataTextViewTime(time_total_tv,totalDuration);
                play_seek.setMax(totalDuration);
                play_seek.setProgress(currentPosition);
                UIHandler.sendEmptyMessageDelayed(1,3000);
            }

        }
    };

    private void setPlayEvent() {
        play_controller_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoview.isPlaying()){
                    play_controller_img.setImageResource(R.drawable.ic_autoplayer);
                    img_hidd.setImageResource(R.drawable.ic_autoplayer);
                    img_hidd.setVisibility(View.VISIBLE);
                    videoview.pause();
                    UIHandler.removeMessages(1);
                }else{
                    play_controller_img.setImageResource(R.drawable.ic_pause);
                    img_hidd.setVisibility(View.GONE);
                    videoview.start();
                    UIHandler.sendEmptyMessage(1);
                }
            }
        });
        play_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updataTextViewTime(current_text, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                UIHandler.removeMessages(1);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                videoview.seekTo(progress);
                UIHandler.sendEmptyMessage(1);
            }
        });
        screen_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFullScreen){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        UIHandler.removeMessages(1);
        if (dbAdapter == null){
            dbAdapter = new DbAdapter(getApplicationContext());
            dbAdapter.open();
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        dater = sdf.format(date);
        try{

        }catch (Exception e){
            if (!titler.equals(null)) {
                dbAdapter.createrecord(Videopather, user, titler, courseName, videoid, Timer, intPositionWhenPause + "", imgPath, dater + "");
            }
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WindowManager manager = (WindowManager)
                getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
           setVideoviewScale(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            isFullScreen = true;
            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, PixelUtils.dp2px(300,getApplicationContext()));
            videoview.setLayoutParams(params);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            Log.e("PIXELUTILS", "onConfigurationChanged: "+PixelUtils.dp2px(200));
            setVideoviewScale(ViewGroup.LayoutParams.MATCH_PARENT,PixelUtils.dp2px(220));
            isFullScreen = false;
            getWindow().clearFlags((WindowManager.LayoutParams.FLAG_FULLSCREEN));
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }


    private void setVideoviewScale(int width,int heght){
        ViewGroup.LayoutParams layoutParams = videoview.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = heght;
        videoview.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams1 = relativeLayout.getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = heght;
        videoview.setLayoutParams(layoutParams1);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.onDestroy();
        dbAdapter.close();
        Log.e("player：","由于系统资源匮乏，被回收了");
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/20  11:43
     *  @方法说明：初始化UI
     */
    private void initUI() {
        PixelUtils.initContent(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        videoview = (VideoView) findViewById(R.id.videoView);
        play_controller_img = (ImageView) findViewById(R.id.pause_img);
        current_text = (TextView) findViewById(R.id.time_current_tv);
        time_total_tv = (TextView) findViewById(R.id.time_total_tv);
        play_seek = (SeekBar) findViewById(R.id.play_seek);
        screen_img = (ImageView) findViewById(R.id.screen_img);
        tv_title_content = (TextView) findViewById(R.id.tv_title_content);//小节标题
        tv_downloader = (TextView) findViewById(R.id.tv_downloader);//下载

        relativeLayout = (RelativeLayout) findViewById(R.id.videolayout);
        img_section = (ImageView) findViewById(R.id.img_sections);
        img_critic = (ImageView) findViewById(R.id.img_critics);
        img_test = (ImageView) findViewById(R.id.img_tests);
        img_question = (ImageView) findViewById(R.id.img_questions);
        tv_courseName = (TextView) findViewById(R.id.tv_courseName);
        img_hidd = (ImageView) findViewById(R.id.img_hidd);
      //  img_readygo = (ImageView) findViewById(R.id.img_readygo);

        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_learns = (TextView) findViewById(R.id.tv_learns);

        img_section.setOnClickListener(this);
        img_critic.setOnClickListener(this);
        img_question.setOnClickListener(this);
        img_test.setOnClickListener(this);
        tv_downloader.setOnClickListener(this);

        tv_section = (TextView) findViewById(R.id.tv_sections);
        tv_critic = (TextView) findViewById(R.id.tv_critics);
        tv_test = (TextView) findViewById(R.id.tv_tests);
        tv_question = (TextView) findViewById(R.id.tv_questions);
        //发送请求
        back_last1=(ImageView) findViewById(R.id.back_last1);//返回键
        QlayEnter enter = new QlayEnter();
        enter.setVideoID(videoid);
        enter.setUseName(user);
        presenter.repuestVideo(enter);
        img_hidd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoview.isPlaying()){
                    play_controller_img.setImageResource(R.drawable.ic_autoplayer);
                    img_hidd.setImageResource(R.drawable.ic_pause);
                    img_hidd.setVisibility(View.VISIBLE);
                    videoview.pause();
                    UIHandler.removeMessages(1);
                }else{
                    play_controller_img.setImageResource(R.drawable.ic_pause);
                    img_hidd.setVisibility(View.GONE);
                    videoview.start();
                    UIHandler.sendEmptyMessage(1);
                }
            }
        });

    }

    @Override
    public void ShowFragment(int index) {
        fm = fram.beginTransaction();
        HideAll();
        switch (index){
            case 1:
                //显示首页
                if(sectionFragment==null){
                    sectionFragment = new SectionFragment();
                    fm.add(R.id.fragemt_content,sectionFragment);
                }
                InitAllFontAndImg(1);
                fm.show(sectionFragment).commit();
                break;
            case 2:
                if(criticFragment == null){
                    criticFragment = new CriticFragment();
                    fm.add(R.id.fragemt_content,criticFragment);
                }
                InitAllFontAndImg(2);
                fm.show(criticFragment).commit();
                break;
            case 3:
                if(testFragment == null){
                    testFragment = new TestFragment();
                    fm.add(R.id.fragemt_content,testFragment);
                }
                InitAllFontAndImg(3);
                fm.show(testFragment).commit();
                break;
            case 4:
                if(questionFragment == null){
                    questionFragment = new QuestionFragment();
                    fm.add(R.id.fragemt_content,questionFragment);
                }
                InitAllFontAndImg(4);
                fm.show(questionFragment).commit();
                break;
        }

    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/23  9:49
     *  @方法说明 显示课程名称
     *  todo
     */
    private  List<sectionBean> listers = new ArrayList<>();
    @Override
    public void showSectionData(videoEnter list) {
      //  lister = list;
        listers = list.getList();
        courseName = list.getCourseName();
        tv_courseName.setText(courseName+"");
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/23  15:35
     *  @方法说明 状态返回
     */
    @Override
    public void ShowToast(String Say) {
        Toast.makeText(getApplicationContext(),""+Say,Toast.LENGTH_SHORT).show();
    }

    public void HideAll() {
        if(sectionFragment!=null){
            fm.hide(sectionFragment);
        }
        if(criticFragment!=null){
            fm.hide(criticFragment);
        }
        if( testFragment != null){
            fm.hide(testFragment);
        }
        if( questionFragment != null){
            fm.hide(questionFragment);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_sections:
                presenter.ShowSection();

                break;
            case R.id.img_critics:
                presenter.ShowCritic();

                break;
            case R.id.img_questions:
                presenter.ShowQuestion();

                break;
            case R.id.img_tests:
                presenter.ShowTest();

                break;
            case R.id.tv_downloader:
                Intent intent = new Intent(this,DownLoadSevers.class);
                if (Videopather.length()>0&&Videopather!=null&&titler.length()>0){
                    intent.putExtra("url",Videopather);
                    intent.putExtra("title",titler);
                    startService(intent);
                }
                break;

        }
    }




    //初始化所有图片的样式，所有字体的颜色
    private void InitAllFontAndImg(int index){


        switch (index){
            case 1:
                //选中第一个
                img_section.setImageResource(R.drawable.ic_zhangjie_click);
                tv_section.setTextColor(getResources().getColor(R.color.colorActionText));
                tv_critic.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_test.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_question.setTextColor(getResources().getColor(R.color.colorDefalutText));
                img_question.setImageResource(R.drawable.ic_wenda);
                img_critic.setImageResource(R.drawable.ic_pinglun);
                img_test.setImageResource(R.drawable.ic_test);
                break;

            case 2:
                //选中第2
                Log.e("选择第二", "第二个边演示");
                img_critic.setImageResource(R.drawable.ic_pinglun_click);
                img_section.setImageResource(R.drawable.ic_zhangjie);
                img_question.setImageResource(R.drawable.ic_wenda);
                img_test.setImageResource(R.drawable.ic_test);
                tv_critic.setTextColor(getResources().getColor(R.color.colorActionText));
                tv_section.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_test.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_question.setTextColor(getResources().getColor(R.color.colorDefalutText));
                break;
            case 3:
                //选中第3
                img_test.setImageResource(R.drawable.ic_test_click);
                img_section.setImageResource(R.drawable.ic_zhangjie);
                img_question.setImageResource(R.drawable.ic_wenda);
                img_critic.setImageResource(R.drawable.ic_pinglun);
                tv_test.setTextColor(getResources().getColor(R.color.colorActionText));
                tv_critic.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_section.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_question.setTextColor(getResources().getColor(R.color.colorDefalutText));
                break;
            case 4:
                //选中第4
                img_critic.setImageResource(R.drawable.ic_pinglun);
                img_section.setImageResource(R.drawable.ic_zhangjie);
                img_test.setImageResource(R.drawable.ic_test);
                img_question.setImageResource(R.drawable.ic_wenda_click);
                tv_question.setTextColor(getResources().getColor(R.color.colorActionText));
                tv_critic.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_section.setTextColor(getResources().getColor(R.color.colorDefalutText));
                tv_test.setTextColor(getResources().getColor(R.color.colorDefalutText));
                break;

        }

    }
    setCriticRequest setcriticRequest = new setCriticRequest();
    onsendTest sendTest = new onsendTest();
    // 3.2 +实现接口，实现回调

    @Override
    public void process(String learnNum, String videoTime,String videoPath,String sectonID,String testID,String title) {
        Log.e("播发视频", "process: "+learnNum);
        if (learnNum.length()!=0&&videoTime.length()!=0) {
            Videopather = videoPath;
            titler = title;
            Timer = videoTime;
            Log.e("时间为·：", "process: "+videoTime+"ssss");
            int time=Integer.parseInt(videoTime);
            int timer = time/60;
            int times = time%60;
            StringBuilder builder = new StringBuilder();
            tv_time.setText(builder.append(timer).append("分").append(times).append("秒").toString());
            tv_title_content.setText(title+"");
            tv_learns.setText("59人");
            play_controller_img.setImageResource(R.drawable.ic_pause);
            //设置网络视频路径
            String uri = StaticCost.IP + videoPath;
            Log.e("URI为", "process: "+uri);
            //播发视频
            playTheVideo(uri);
          //  videoView.setVideoURI(Uri.parse(uri));
            //TODO 记得修改
            setcriticRequest.setCourseID(videoid);
            setcriticRequest.setSectionID(sectonID);
            Log.e("testID:sectonID",sectonID+"---"+sectonID);
            sendTest.setTestid(testID);
            sendTest.setSid(sectonID);
            Log.e("数据已经存入setCriticRequest","setCriticRequest: ");
        }
    }
//向Criticfragment传值
    public String getcriticProcess() {
        return videoid;
    }
//TestFtagment传值
    public onsendTest getTestProcess(){
        return sendTest;
    }
    //向questionfragment传值
    public String getQuestionCourseId(){
        return videoid;
    }
    public String getTitles(){
        return "hello";
    }


}
