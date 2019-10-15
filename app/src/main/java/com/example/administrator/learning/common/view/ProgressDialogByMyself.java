package com.example.administrator.learning.common.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.learning.R;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

public class ProgressDialogByMyself extends ProgressDialog{
    public View view;
    public TextView textView;
    public String texts;
    public ProgressDialogByMyself(Context context) {
        //super(context);
        this(context,null);
    }



    public ProgressDialogByMyself(Context context,String text) {
        super(context, R.style.CustomDialog);
        this.texts = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }
    private void init(Context context) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        //View view = View.inflate(context,R.layout.dialog_pro,null);
        setContentView(R.layout.dialog_pro);//loading的xml文件
        WindowManager.LayoutParams params = getWindow().getAttributes();
        textView = (TextView) findViewById(R.id.tv_load_dialog);

        if(texts != null){
            //textView.setText(texts);
        }

        params.width = (WindowManager.LayoutParams.WRAP_CONTENT);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }
    @Override
    public void show() {//开启
        super.show();
    }
    @Override
    public void dismiss() {//关闭
        super.dismiss();
    }



}
