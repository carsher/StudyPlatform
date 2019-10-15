package com.example.administrator.learning.main.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.common.view.ProgressDialogByMyself;
import com.example.administrator.learning.home.view.HomeActivity;
import com.example.administrator.learning.main.model.AccountMangerImp;
import com.example.administrator.learning.main.presenter.ISelectSchoolpresnter;
import com.example.administrator.learning.main.presenter.SelectSchoolpresnterImp;

import java.util.ArrayList;

public class SelectSchoolActivity extends AppCompatActivity implements ISelectSchoolView{
    public TextView tv_outthis;
    public EditText edt_no,edt_name;
    public Button btn_finsh;
    public ISelectSchoolpresnter presnter;
    public String schoolname;
    public ProgressDialogByMyself mDialog;
    public String schoolnumber = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        setContentView(R.layout.activity_select_school);
        mDialog = new ProgressDialogByMyself(this);
        setNavigationBarStatusBarTranslucent(this);
        initUI();

    }

    private void initUI() {
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_no = (EditText) findViewById(R.id.edt_no);
        btn_finsh = (Button) findViewById(R.id.btn_finsh);
        tv_outthis = (TextView) findViewById(R.id.tv_outthis);

        btn_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =  edt_name.getText().toString();
                String no =  edt_no.getText().toString();

                    mDialog.show();
                    presnter.selectSchool(name,no);
                }

            });

        tv_outthis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.remove(getApplicationContext(), StaticCost.USER);
                SpUtils.remove(getApplicationContext(),StaticCost.THESCHOOL);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
           });
    }

    @Override
    public void showHome() {
        mDialog.dismiss();
        /*选择学校成功[添加到sp中]*/
        SpUtils.putString(this,StaticCost.SCHOOLNAME,schoolname);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void say(String say) {
        mDialog.dismiss();
        Toast.makeText(this, say , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showSchoolList(ArrayList list) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( presnter ==null){
            presnter = new SelectSchoolpresnterImp(this,new AccountMangerImp(OkHttpImp.getInstance()),getApplicationContext());
            presnter.register();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presnter.onDestroy();
    }
    public void setNavigationBarStatusBarTranslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            //LinearLayout parent_layout = (LinearLayout) findViewById(R.id.parent_layout);
            // parent_layout.setPadding(0,0,0,parent_layout.getPaddingBottom());
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
