package com.example.administrator.learning.video.view;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.IQuestionPresenter;
import com.example.administrator.learning.video.presenter.QuestionPresenter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public class QuestionFragment extends Fragment implements IQuestionFragment{

    private View view;
    private ListView lv_question;
    private ImageView add_question;
    private String courseId="1";//课程ID
    private IQuestionPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question,null);
        if (presenter == null){
            presenter = new QuestionPresenter(this, new VideoMangerImp(OkHttpImp.getInstance()));
            presenter.register();
        }
        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        courseId = ((Qlayeractivity)activity).getQuestionCourseId();//课程id
        Log.e("QLAYERACTIVITY又是你啊", "onAttach: " + courseId);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        if (presenter == null){
            presenter = new QuestionPresenter(this, new VideoMangerImp(OkHttpImp.getInstance()));
            presenter.register();
        }
        presenter.getQuestionlist(courseId);
    }

    private void initUI() {
        lv_question = (ListView) view.findViewById(R.id.lv_question);
        add_question = (ImageView) view.findViewById(R.id.add_question);

        add_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddQuestion.class);
                intent.putExtra("CourseID",courseId);
                startActivity(intent);
            }
        });
    }
    //展示问答列表 数据
    @Override
    public void ShowData(final List<HashMap> list) {
        lv_question.setAdapter(new myAdapter_question(list));
        lv_question.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),PostActivity.class);
                intent.putExtra(IQuestionPresenter.ID,(position+1)+"");
                intent.putExtra(IQuestionPresenter.TITLE,list.get(position).get(IQuestionPresenter.TITLE)+"");
                intent.putExtra(IQuestionPresenter.CONTENT,list.get(position).get(IQuestionPresenter.CONTENT)+"");
                intent.putExtra(IQuestionPresenter.USERS,list.get(position).get(IQuestionPresenter.USERS)+"");
                intent.putExtra(IQuestionPresenter.TIME,list.get(position).get(IQuestionPresenter.TIME)+"");
                startActivity(intent);
            }
        });
    }
    //展示错误
    @Override
    public void ShowToash(String say) {
        Toast.makeText(getActivity(),say+"",Toast.LENGTH_SHORT).show();
    }
    //点击的条目设置点击事


    private class myAdapter_question extends BaseAdapter {
        private List<HashMap> list;
        public myAdapter_question(List<HashMap> list){
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(),R.layout.item_question,null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title_question);
            TextView tv_time = (TextView) view.findViewById(R.id.tv_questionTime);
            TextView tv_content = (TextView) view.findViewById(R.id.tv_content_question);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_questionName);

            HashMap map = list.get(position);
            Log.e("QuestionFragment", "getView: "+map.get(IQuestionPresenter.TITLE)+"ssss");

            tv_title.setText(map.get(IQuestionPresenter.TITLE)+"");
            tv_content.setText(map.get(IQuestionPresenter.CONTENT)+"");
            tv_name.setText(map.get(IQuestionPresenter.USERS)+"");
            tv_time.setText(map.get(IQuestionPresenter.TIME)+"");

            return view;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter.onDestroy();
    }
}
