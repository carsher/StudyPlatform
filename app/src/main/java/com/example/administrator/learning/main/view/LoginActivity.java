package com.example.administrator.learning.main.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.common.view.ProgressDialogByMyself;
import com.example.administrator.learning.home.view.HomeActivity;
import com.example.administrator.learning.main.model.AccountMangerImp;
import com.example.administrator.learning.main.presenter.ILoginpresenter;
import com.example.administrator.learning.main.presenter.LoginpresenterImp;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    public EditText edt_phone,edt_pwd;
    public Button btn_login;
    public TextView btn_reigster;
    public ILoginpresenter presenter;
    public ProgressDialogByMyself mdialog;
    public int STUDENT = 1,TEACHER = 2;
    public int loginrow = STUDENT;//学生
    private String type;

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/17  16:42
     *  @方法说明：登录页面
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginpresenterImp(new AccountMangerImp(OkHttpImp.getInstance()),this);
        presenter.register();
        mdialog = new ProgressDialogByMyself(this);
//        Intent intent = getIntent();
//        type = intent.getStringExtra("type");
        initUI();
    }

    private void initUI() {
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_reigster = (TextView) findViewById(R.id.btn_reigster);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录按钮点击
                mdialog.show();
                    //学生登录
                    presenter.login(edt_phone.getText().toString(),edt_pwd.getText().toString(),getApplicationContext());

            }
        });
        btn_reigster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开注册页面
                Intent intent = new Intent(getApplication(),ReigsterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.action_in,R.anim.action_out);
            }
        });

    }

    @Override
    public void ShowPullSchool() {
        mdialog.dismiss();
        Intent intent = new Intent(getApplicationContext(),SelectSchoolActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ShowHome() {
        mdialog.dismiss();
        SpUtils.putString(getApplicationContext(),StaticCost.USER,edt_phone.getText().toString());
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void ShowToast(String say) {
        mdialog.dismiss();
        Toast.makeText(getApplicationContext(),say,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.onDestroy();
    }
}
