package com.example.administrator.learning.main.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.IhttpClient;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.severs.GetPushMessage;
import com.example.administrator.learning.common.view.ProgressDialogByMyself;
import com.example.administrator.learning.main.common.AccountEntity;
import com.example.administrator.learning.main.model.AccountMangerImp;
import com.example.administrator.learning.main.presenter.IReigsterpresenter;
import com.example.administrator.learning.main.presenter.ReigsterpresenterImp;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.PushService;


public class ReigsterActivity extends AppCompatActivity implements IReigsterView{
    public IReigsterpresenter presenter;
    public ProgressDialogByMyself mDialog;
    public EditText edt_phone,edt_pwd,edt_verify,edt_name,edt_Number;
    public Button btn_reigster;
    public TextView tv_sendcode;
    public boolean sendcode = false;
    public int time = 120;
    public String type = "stu";

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time = time-1;
            tv_sendcode.setText("剩余"+time+"秒");
            if(time == 0){
                tv_sendcode.setText("获取验证码");
                sendcode = false;
            }
        }
    };
    private Spinner spinner_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册
        PushManager.getInstance().initialize(this.getApplicationContext(), PushService.class);
        //注册接收推送的Sever
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), GetPushMessage.class);
        setContentView(R.layout.activity_reigster);
        mDialog = new ProgressDialogByMyself(this);
        initUI();
        setNavigationBarStatusBarTranslucent(this);

    }

    private void initUI() {
        tv_sendcode = (TextView) findViewById(R.id.tv_sendcode);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        edt_verify = (EditText) findViewById(R.id.edt_verify);
        btn_reigster = (Button) findViewById(R.id.btn_reigster);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_Number = (EditText) findViewById(R.id.edt_studentNumber);

        btn_reigster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
                String phone = edt_phone.getText().toString();
                String pwd = edt_pwd.getText().toString();
                String code = edt_verify.getText().toString();
                String Number = edt_Number.getText().toString();
                if (phone.isEmpty()||pwd.isEmpty()||code.isEmpty()||Number.isEmpty()){
                    Toast.makeText(getApplicationContext(),"填写信息不能为空！",Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }else {
                    AccountEntity entity = new AccountEntity();
                    entity.setAccount(phone);
                    entity.setPassword(pwd);
                    entity.setCode(code);
                    entity.setType(type);
                    entity.setNumber(Number);
                /*获取一下cid*/
                    String cid = PushManager.getInstance().getClientid(getApplicationContext());
                    entity.setCid(cid);

                    presenter.registerUser(entity);
                    //TODO 记得注释
//                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
//                intent.putExtra("type",type);
//                startActivity(intent);
                }
            }
        });

        tv_sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //下发验证码
                if(sendcode){
                    return;
                }
                mDialog.show();
                String phone = edt_phone.getText().toString();
                String Number = edt_Number.getText().toString();
                if(phone.length() != 11 ){
                    say("您输入的手机号码有误");
                }else{
                    presenter.sendCode(phone);
                }
            }
        });

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

    @Override
    public void say(String say) {
        mDialog.dismiss();
        //Toast.makeText(getActivity().getApplicationContext(),"网络请求错误",Toast.LENGTH_LONG).show();
        final AlertDialog.Builder dialogs = new AlertDialog.Builder(this);
        dialogs.setMessage(say);
        dialogs.setTitle("系统提示");
        dialogs.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogs.show();
    }

    @Override
    public void ShowLogin() {
        mDialog.dismiss();
        Toast.makeText(this,"注册成功，返回登录",Toast.LENGTH_SHORT).show();
        presenter.unregister();
        //TODO 暂时没后台先注释
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
        finish();
    }

    @Override
    public void sendCodeSuc() {
        //验证码下发成功
        Log.e("发送成功","弹出框消失");
        sendcode = true;
        mDialog.dismiss();
        new Thread(){
            @Override
            public void run() {
                super.run();
                for (;;){
                    SystemClock.sleep(1000);
                    handler.sendEmptyMessage(0);
                    if(!sendcode){
                        break;
                    }
                }
            }
        }.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       // finish();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(presenter == null){
            presenter = new ReigsterpresenterImp(new AccountMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
    }

}
