package com.example.administrator.learning.video.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.common.view.ProgressDialogByMyself;
import com.example.administrator.learning.video.common.bean.onQuestionEntity;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.AddQuestionPresenter;
import com.example.administrator.learning.video.presenter.IAddQuestionPresenter;
import com.example.administrator.learning.video.presenter.IQuestionPresenter;
import com.example.administrator.learning.video.presenter.QuestionPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuestion extends AppCompatActivity implements IAddQuestion{

    private EditText edt_title;
    private EditText edt_content;
    private String cid;
    private IAddQuestionPresenter presenter;
    private Button brn_addQuestion;
    public ProgressDialogByMyself mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        if (presenter == null){
            presenter = new AddQuestionPresenter(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        mDialog = new ProgressDialogByMyself(this);
        Intent intent = getIntent();
        cid = intent.getStringExtra("CourseID");
        initUI();

    }

    private void initUI() {
        edt_content = (EditText) findViewById(R.id.edt_content);
        edt_title = (EditText) findViewById(R.id.edt_title);
        brn_addQuestion = (Button) findViewById(R.id.add_Reviews);

        brn_addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
                String title = edt_title.getText().toString();
                String content = edt_content.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(System.currentTimeMillis());
                String dater = sdf.format(date);



                onQuestionEntity entity = new onQuestionEntity();
                entity.setTitle(title);
                entity.setContent(content);
                entity.setCid(cid);
                entity.setUser(SpUtils.getString(getApplicationContext(), StaticCost.USER));
                entity.setTime(dater);
                presenter.getReplyQuestion(entity);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.onDestroy();
    }

    @Override
    public void ShowData(String say) {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(),say,Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void ShowToash(String say) {
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(),say,Toast.LENGTH_SHORT).show();
        finish();
    }
}
