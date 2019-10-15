package com.example.administrator.learning.home.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;

/**
 * Created by Administrator on 2018/10/19 0019.
 */

public class UserFragment extends Fragment implements View.OnClickListener{
    private View view;
    private TextView tv_usename;
    private TextView tv_section;
    private ImageView ic_scord;
    private ImageView img_jilu;
    private ImageView img_yijian;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user,null);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUi();
        initData();
    }

    private void initData() {
        String name = SpUtils.getString(getActivity(), StaticCost.USER);
        tv_usename.setText("姓名:"+"姚相业");
        tv_section.setOnClickListener(this);
    }

    private void initUi() {
        tv_usename = (TextView) view.findViewById(R.id.tv_username);
        tv_section = (TextView) view.findViewById(R.id.tv_settion);
        ic_scord = (ImageView) view.findViewById(R.id.ic_scord);
        img_jilu = (ImageView) view.findViewById(R.id.img_jilu);
        img_yijian = (ImageView) view.findViewById(R.id.img_yijian);

        ic_scord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(),ScoredRecordActivity.class);
                startActivity(in);
            }
        });
        img_jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent in = new Intent(getActivity(),RecordFragment.class);
                //startActivity(in);
                Toast.makeText(getActivity(),"等待开发中！",Toast.LENGTH_SHORT).show();
            }
        });
        img_yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent in = new Intent(getActivity(),RecordFragment.class);
                //startActivity(in);
                Toast.makeText(getActivity(),"等待开发中！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(),OutLoginActivity.class);
        startActivity(intent);
    }
}
