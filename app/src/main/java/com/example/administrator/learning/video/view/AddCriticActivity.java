package com.example.administrator.learning.video.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.common.view.ProgressDialogByMyself;
import com.example.administrator.learning.video.common.bean.DynamicEntity;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.IDynamicpresenter;
import com.example.administrator.learning.video.presenter.SendDynamicpresenterImp;

import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class AddCriticActivity extends AppCompatActivity implements View.OnClickListener,IAddDynamicView{
    public ProgressDialogByMyself progressDialogByMyself;
    private EditText edt_contenter;
    private Button add_reviews;
    private String courseId="1";
    public IDynamicpresenter presenter;
    private String user;
    private String content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_critic);
        progressDialogByMyself = new ProgressDialogByMyself(this, "正在发布中...");
        if (presenter == null) {
            presenter = new SendDynamicpresenterImp(getApplicationContext(),new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        initUI();
        initData();
    }

    private void initUI() {
        edt_contenter = (EditText) findViewById(R.id.edt_content_adder);
        add_reviews = (Button) findViewById(R.id.add_Reviews);
        add_reviews.setOnClickListener(this);
    }

    private void initData() {
        if (edt_contenter == null){
            Log.e("ssssssssssssssssss", "initData: " );
        }
        Intent intent = getIntent();
        courseId = intent.getStringExtra("courseID");
        user = intent.getStringExtra("user");
        content = edt_contenter.getText().toString();
    }
    //点击发布提交数据给小明同学
    @Override
    public void onClick(View v) {
        //发布消息
        //显示加载中的对话框
        content = edt_contenter.getText().toString();

        progressDialogByMyself.show();
        DynamicEntity entity = new DynamicEntity();
        entity.setUser(SpUtils.getString(getApplicationContext(), StaticCost.USER));
        entity.setCourseId(courseId);
        entity.setContent(edt_contenter.getText().toString());
        Log.e("------------------","courseId"+courseId+"_____-"+content+"----------");

        presenter.sendDynamicJustText(entity);

    }

    @Override
    public void onSend(int doing, String requestId) {
        //发布成功
        if (progressDialogByMyself != null) {
            if (progressDialogByMyself.isShowing()) {
                progressDialogByMyself.dismiss();
            }
        }
//        Intent intent = new Intent(getApplicationContext(), CriticFragment.class);
//        intent.putExtra("id", requestId);
//        intent.putExtra("position", -1);
//        startActivity(intent);
        AddCriticActivity.this.finish();
    }

    @Override
    public void showToast(String say) {
        if (progressDialogByMyself != null) {
            if (progressDialogByMyself.isShowing()) {
                progressDialogByMyself.dismiss();
            }
        }
        Toast.makeText(getApplicationContext(), say, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unregister();
            presenter.onDestroy();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter == null) {
            presenter = new SendDynamicpresenterImp(getApplicationContext(),new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
    }
/*
* 先创建一个被观察者，被观察者在被subscribe(被订阅时call()会被调用),
* 在call()中call()被调用则OnSubscriber的call就会被调用，
* 事件序列就会依照设定依次触发即观察者的会被依次调用；
* 加载图片将会发生在 IO 线程，
* 而设置图片则被设定在了主线程。
* */
//    public void testExjavaimg(){
//        final int drawableRes = R.drawable.loding_img;
//        final ImageView imageView = new ImageView(this);
//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
////            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = getTheme().getDrawable(drawableRes));
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//          .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//          .subscribe(new Observer<Drawable>() {
//                    @Override
//                    public void onNext(Drawable drawable) {
//                        imageView.setImageDrawable(drawable);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

}
