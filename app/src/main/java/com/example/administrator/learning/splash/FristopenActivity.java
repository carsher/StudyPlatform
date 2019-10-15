package com.example.administrator.learning.splash;

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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.learning.R;
import com.example.administrator.learning.main.view.LoginActivity;
import com.example.administrator.learning.main.view.ReigsterActivity;

import java.lang.reflect.Method;

public class FristopenActivity extends AppCompatActivity {
    public Button btn_login,btn_reigster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fristopen);
        setNavigationBarStatusBarTranslucent(this);//沉浸式通知栏
        initUI();
    }
    private void initUI() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_reigster = (Button) findViewById(R.id.btn_reigster);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开登录页面
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.action_in,R.anim.action_out);
            }
        });

        btn_reigster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReigsterActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.action_in,R.anim.action_out);
            }
        });
    }

    public void setNavigationBarStatusBarTranslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            boolean is = checkDeviceHasNavigationBar(this);
            if(is){
                int height =  getStatusBarHeight(this);
                LinearLayout parent_layout = (LinearLayout) activity.findViewById(R.id.ly_bootm);
                parent_layout.setPadding(parent_layout.getPaddingLeft(),parent_layout.getPaddingTop(),parent_layout.getPaddingRight(),parent_layout.getPaddingBottom()+ 2*height -13);
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
}
