package com.example.administrator.learning.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.home.view.HomeActivity;
import com.example.administrator.learning.video.view.Qlayeractivity;

/**
 *  @author crash
 *  @time   2018/10/16 14:44
 *  @describe 引导页 项目入口点
 */
public class MainActivity extends AppCompatActivity {
@SuppressLint("HandlerLeak")
private Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {

        Log.e("测试输出用户名：" , SpUtils.getString(getApplicationContext(), StaticCost.NAME)+"---班级---"+SpUtils.getString(getApplicationContext(),StaticCost.CLASSNAME));
        Log.e("是否绑定学生个人信息:",SpUtils.getString(getApplicationContext(),StaticCost.ISBIND)+"(0->未绑定,1->已经绑定)");
        String name = SpUtils.getString(getApplicationContext(),StaticCost.USER);
        if(name.equals("null")){
            Intent intent1 = new Intent(getApplicationContext(), FristopenActivity.class);
            startActivity(intent1);
            finish();
        }else{
            Intent intent2 = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent2);
            finish();        }
    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler.sendEmptyMessageDelayed(1,2000);
    }
}
