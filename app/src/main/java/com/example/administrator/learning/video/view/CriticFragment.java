package com.example.administrator.learning.video.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.video.common.bean.setCriticRequest;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.GetCriticpresenter;
import com.example.administrator.learning.video.presenter.ICriticPresenter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public class CriticFragment extends Fragment implements ICriticFragment{
    private View view;
    private ImageView add_critic;
    private ListView lv_critic;
    public ICriticPresenter presenter;
    private String bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_critic,null);
        if (presenter == null){
            presenter = new GetCriticpresenter(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
            getContentDynamic();
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        bean = ((Qlayeractivity)activity).getcriticProcess();
        Log.e("CRITIC又是你啊", "onAttach: " +bean);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void initUI() {
        add_critic = (ImageView) view.findViewById(R.id.add_critic);
        lv_critic = (ListView) view.findViewById(R.id.lv_critic);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void getContentDynamic() {
    //    String couseID = bean.getCourseID();
      //  String sectionID = bean.getSectionID();
     //   presenter.getContentDynamic(couseID,sectionID);
        presenter.getContentDynamic(bean);
    }


    @Override
    public void ShowContentDynamic(List<HashMap> list) {
        lv_critic.setAdapter(new MyAdapter_critic(list));
        add_critic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddCriticActivity.class);
                intent.putExtra("courseID",bean);
                intent.putExtra("user", SpUtils.getString(getActivity(), StaticCost.USER));
                startActivity(intent);

            }
        });
    }

    @Override
    public void ShowToash(String say) {
        Toast.makeText(getActivity(),say,Toast.LENGTH_SHORT).show();
    }
    /**
     *  @创建用户 crash
     *  @创建时间 2018/11/05  13:39
     *  @方法说明：展示评论
     */
    private class MyAdapter_critic extends BaseAdapter {
        private List<HashMap> list;
        public MyAdapter_critic(List<HashMap> list) {
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
            View view = View.inflate(getActivity(),R.layout.item_critic,null);
            TextView tv_Name = (TextView) view.findViewById(R.id.tv_criticName);
            TextView tv_Time = (TextView) view.findViewById(R.id.tv_criticTime);
            TextView tv_content = (TextView) view.findViewById(R.id.tv_content_critic);
            TextView tv_Like = (TextView) view.findViewById(R.id.tv_clickLike);

            HashMap map = list.get(position);
            tv_Name.setText(map.get(ICriticPresenter.USER)+"");
            tv_Time.setText(map.get(ICriticPresenter.TIME)+"");
            tv_content.setText(map.get(ICriticPresenter.SAY)+"");

            return view;
        }
    }
}
