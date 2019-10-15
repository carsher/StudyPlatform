package com.example.administrator.learning.home.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.home.model.MainMangerImp;
import com.example.administrator.learning.home.presenter.HomepresenterImp;
import com.example.administrator.learning.home.presenter.IHomepresenter;
import com.example.administrator.learning.main.view.LoginActivity;
import com.example.administrator.learning.main.view.SelectSchoolActivity;

import java.lang.reflect.Method;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener,IHomeView{
    private HomeFragment homefragment = null;
    private DownloadFragment downloadfragment=null;
    private RecordFragment recordFragment = null;
    private UserFragment userFragment = null;

    public IHomepresenter presenter;
    public FrameLayout fm_content;
    public LinearLayout ly_home,ly_stack,ly_chioce,ly_user;
    public TextView tv_home,tv_stack,tv_chice,tv_user;
    public ImageView iv_home,iv_stack,iv_chice,iv_user;
    public ImageView header_run,header_stack,header_chioce,header_user;
    private static FragmentTransaction fm;
    private android.support.v4.app.FragmentManager fram = getSupportFragmentManager();
    private static int savePage=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
          super.onCreate(savedInstanceState);

            Log.e("HomeActivity,onCreate", "savedInstanceStatew为空");
            if (presenter == null) {
                presenter = new HomepresenterImp(new MainMangerImp(OkHttpImp.getInstance()), this);
                presenter.register();
            }
            setContentView(R.layout.activity_home);
            setNavigationBarStatusBarTranslucent(this);
            //插入一条测试数据
//            presenter.IsLogin(getApplicationContext());
            initView(true);

        }else{
            super.onCreate(null);
            Log.e("HomeActivity,onCreate","savedInstanceStatew不为空" + savedInstanceState.getInt("show"));
            int page_Index = savedInstanceState.getInt("show");
            if(presenter == null){
                presenter = new HomepresenterImp(new MainMangerImp(OkHttpImp.getInstance()),this);
                presenter.register();
            }
            setContentView(R.layout.activity_home);
            setNavigationBarStatusBarTranslucent(this);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            initView(false);
            ShowFragment(page_Index);
        }
    }

    public void setNavigationBarStatusBarTranslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            boolean is = checkDeviceHasNavigationBar(this);
            if(is){
                int height =  getStatusBarHeight(this);
                LinearLayout parent_layout = (LinearLayout) findViewById(R.id.parent_layout);//13
                parent_layout.setPadding(0,0,0,parent_layout.getPaddingBottom()+ 2*height -13   );
            }

            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
            // activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);

            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < 21 ) {
            Window window = activity.getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }

    public int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }

    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }


    private void initView(boolean isShowFrist) {
        ly_home = (LinearLayout) findViewById(R.id.ly_home);
        ly_chioce = (LinearLayout) findViewById(R.id.ly_chioce);
        ly_stack = (LinearLayout) findViewById(R.id.ly_stack);
        ly_user = (LinearLayout) findViewById(R.id.ly_user);
        fm_content = (FrameLayout) findViewById(R.id.fragemt_content);
        /*初始化处理fragment*/
        ly_home.setOnClickListener(this);
        ly_stack.setOnClickListener(this);
        ly_chioce.setOnClickListener(this);
        ly_user.setOnClickListener(this);

        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_stack = (TextView) findViewById(R.id.tv_stack);//下载
        tv_chice = (TextView) findViewById(R.id.tv_chioce);//记录
        tv_user = (TextView) findViewById(R.id.tv_user);

        header_run = (ImageView) findViewById(R.id.header_run);
        header_stack = (ImageView) findViewById(R.id.header_stack);
        header_chioce = (ImageView) findViewById(R.id.header_chioce);
        header_user = (ImageView) findViewById(R.id.header_user);

        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_stack = (ImageView) findViewById(R.id.iv_stack);
        iv_chice = (ImageView) findViewById(R.id.iv_chioce);
        iv_user = (ImageView) findViewById(R.id.iv_user);

        fm = fram.beginTransaction();


        if(isShowFrist){
            presenter.ShowHome();//显示首页
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.onDestroy();
        Log.e("HomeActivity：","由于系统资源匮乏，被回收了");
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/18  11:39
     *  @方法说明：监听各个 线性布局的点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ly_home:
                // ly_home.setBackgroundColor(getResources().getColor(R.color.Gainsboro));
                presenter.ShowHome();

                break;
            case R.id.ly_stack:
                //  ly_stack.setBackgroundColor(getResources().getColor(R.color.Gainsboro));
                presenter.ShowStack();

                break;
            case R.id.ly_chioce:
                // ly_chioce.setBackgroundColor(getResources().getColor(R.color.Gainsboro));
                presenter.ShowChoice();

                break;
            case R.id.ly_user:
                // ly_user.setBackgroundColor(getResources().getColor(R.color.Gainsboro));
                presenter.ShowUser();

                break;
        }
    }



    @Override
    public void ShowFragment(int index) {
        fm = fram.beginTransaction();
        HideAll();
        switch (index){
            case 1:
                //显示首页
                if(homefragment==null){
                    homefragment = new HomeFragment();
                    fm.add(R.id.fragemt_content,homefragment);
                }
                InitAllFontAndImg(1);
                fm.show(homefragment).commit();
                savePage = 1;
                break;
            case 2:
                if(downloadfragment == null){
                    downloadfragment = new DownloadFragment();
                    fm.add(R.id.fragemt_content,downloadfragment);
                }
                InitAllFontAndImg(2);
                fm.show(downloadfragment).commit();
                savePage = 2;
                break;
            case 3:
                if(recordFragment == null){
                    recordFragment = new RecordFragment();
                    fm.add(R.id.fragemt_content,recordFragment);
                }
                InitAllFontAndImg(3);
                fm.show(recordFragment).commit();
                savePage = 3;
                break;
            case 4:
                if(userFragment == null){
                    userFragment = new UserFragment();
                    fm.add(R.id.fragemt_content,userFragment);
                }
                InitAllFontAndImg(4);
                fm.show(userFragment).commit();
                savePage = 4;
                break;
        }

    }

    @Override
    public void ShowSelectSchool() {
        Intent intent = new Intent(this,SelectSchoolActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void close() {
        finish();
    }

    /**
     *  @创建用户 somafish
     *  @创建时间 2018/11/12  10:15
     *  @方法说明：隐藏所有fragment 初始化所有的样式
     */
    public void HideAll() {
        if(homefragment!=null){
            fm.hide(homefragment);
        }
        if(downloadfragment!=null){
            fm.hide(downloadfragment);
        }
        if( recordFragment != null){
            fm.hide(recordFragment);
        }
        if( userFragment != null){
            fm.hide(userFragment);
        }
    }

    //初始化所有图片的样式，所有字体的颜色
    private void InitAllFontAndImg(int index){
        tv_home.setTextColor(getResources().getColor(R.color.colorDefalutText));
        tv_user.setTextColor(getResources().getColor(R.color.colorDefalutText));
        tv_chice.setTextColor(getResources().getColor(R.color.colorDefalutText));
        tv_stack.setTextColor(getResources().getColor(R.color.colorDefalutText));
        iv_home.setImageResource(R.drawable.ic_home);
        iv_user.setImageResource(R.drawable.ic_my);
        iv_chice.setImageResource(R.drawable.ic_jilu);
        iv_stack.setImageResource(R.drawable.ic_downloader);

        header_run.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        header_chioce.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        header_stack.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        header_user.setBackgroundColor(getResources().getColor(R.color.colorWhite));

        switch (index){
            case 1:
                //选中第一个
                iv_home.setImageResource(R.drawable.ic_home_click);
                tv_home.setTextColor(getResources().getColor(R.color.colorActionText));
                header_run.setBackgroundColor(getResources().getColor(R.color.colorActionText));
                break;

            case 2:
                //选中第2
                Log.e("选择第二", "第二个边演示");
                header_stack.setBackgroundColor(getResources().getColor(R.color.colorActionText));
                iv_stack.setImageResource(R.drawable.ic_downloader_click);
                tv_stack.setTextColor(getResources().getColor(R.color.colorActionText));
                break;
            case 3:
                //选中第3
                header_chioce.setBackgroundColor(getResources().getColor(R.color.colorActionText));
                iv_chice.setImageResource(R.drawable.ic_jilu_click);
                tv_chice.setTextColor(getResources().getColor(R.color.colorActionText));
                break;
            case 4:
                //选中第4
                iv_user.setImageResource(R.drawable.ic_my_click);
                tv_user.setTextColor(getResources().getColor(R.color.colorActionText));
                header_user.setBackgroundColor(Color.parseColor("#00ba78"));
                break;

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //退出
        AlertDialog.Builder dialog  = new AlertDialog.Builder(this);
        dialog.setTitle("退出？");
        dialog.setMessage("是否确认退出程序？");
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        dialog.show();
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //系统当此activity出于非顶栈时调用，被回收，记录住当前显示那个页面
        outState.putInt("show",1);
        super.onSaveInstanceState(outState);
    }
}
