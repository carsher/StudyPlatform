package com.example.administrator.learning.home.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.utils.Const;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.main.view.LoginActivity;

public class OutLoginActivity extends AppCompatActivity implements View.OnClickListener{
    public Dialog mCameraDialog;

    private RelativeLayout rtl_outlogin;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_login);
        setNavigationBarStatusBarTranslucent(this);
        initUI();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutLoginActivity.this.finish();
            }
        });
    }

    private void initUI() {
        back=(ImageView)findViewById(R.id.iv_back1) ;
        rtl_outlogin = (RelativeLayout) findViewById(R.id.rtl_outlogin);
        rtl_outlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog();
            }
        });

    }

    private void setDialog() {
        mCameraDialog = new Dialog(this, R.style.dialog);
        RelativeLayout root = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.dialog_buttom_meau, null);
        //初始化视图
        root.findViewById(R.id.btn_outlogin).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    public void onClick(View v) {
        //按钮的点击事件
        switch (v.getId()){
            case R.id.btn_outlogin:
                //退出登录
                mCameraDialog.dismiss();
                SpUtils.removeAll(getApplicationContext());
                //退出，显示登录页面
                Intent date = new Intent(OutLoginActivity.this, LoginActivity.class);
//                date.putExtra(Const.CLOASE_VAL,Const.CLOSE_OUTLOGIN);
//                setResult(OutLoginActivity.CLOSE_CODE,date);
                startActivity(date);
                finish();
                //动画
                break;
            case R.id.btn_cancel:
                //取消
                mCameraDialog.dismiss();
                break;
        }
    }

    public void setNavigationBarStatusBarTranslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {

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
}
