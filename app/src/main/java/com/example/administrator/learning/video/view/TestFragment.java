package com.example.administrator.learning.video.view;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.learning.R;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.video.common.bean.onsendTest;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.ISelectPresenterImp;
import com.example.administrator.learning.video.presenter.ITestPresenter;
import com.example.administrator.learning.video.presenter.TestPresenter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/20 0020.
 */

public class TestFragment extends Fragment implements ITestFragment,View.OnClickListener{
    private View view;
    private FrameLayout fragment_content;
    private LinearLayout lin_test;
    private Button btn_up;
    private Button btn_next;
    private View activity_entitle;
    private RadioButton fourRadio,thirdRadio,secondRadio,firstRadio;
    private RadioGroup radioGroup;
    private TextView tx_testtitle;
    private Button btn_submit;
    private ImageView ic_load;
    private ITestPresenter presenter;
    private onsendTest bean;
    private int i = 0;//试题
    private List<HashMap> lister;
    private int[] score = new int[]{};


    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/29  18:54
     *  @方法说明 课程小节测试
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test,null);
        if(presenter == null){
            presenter = new TestPresenter(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        if (bean.getTestid()==null||Integer.parseInt(bean.getTestid())==0||bean.getTestid().length()<0){
            Log.e("bean.getTestid()为空！", bean.getTestid()+"onCreateView: 为空！");
            Toast.makeText(getActivity(),"你还没有选择章节哦!",Toast.LENGTH_SHORT).show();
        }else{
            Log.e("bean.getTestid()", bean.getTestid()+"！");
            Toast.makeText(getActivity(),"开始计时测试!",Toast.LENGTH_SHORT).show();
            presenter.getTestWord(bean.getTestid());
        }
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        bean = ((Qlayeractivity)activity).getTestProcess();
        Log.e("TEST又是你啊", "onAttach: " +bean.getTestid());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUi();
    }

    private void initUi() {
        fragment_content = (FrameLayout) view.findViewById(R.id.fragemt_content);
        activity_entitle = View.inflate(getActivity(), R.layout.activity_entitle, null);//题目
        lin_test = (LinearLayout) view.findViewById(R.id.lin_test);        //包裹上下题目的布局
        btn_up = (Button) view.findViewById(R.id.btn_up);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        ic_load = (ImageView) view.findViewById(R.id.ic_load);

        tx_testtitle = (TextView) activity_entitle.findViewById(R.id.tx_testtltle);
        radioGroup = (RadioGroup) activity_entitle.findViewById(R.id.radioGroup);
        firstRadio = (RadioButton) activity_entitle.findViewById(R.id.firstRadio);
        secondRadio = (RadioButton) activity_entitle.findViewById(R.id.secondRadio);
        thirdRadio = (RadioButton) activity_entitle.findViewById(R.id.thirdRadio);
        fourRadio = (RadioButton) activity_entitle.findViewById(R.id.fourRadio);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.onDestroy();
    }

    @Override
    public void ShowToash(String say) {
//        radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
        tx_testtitle.setText(""+say);
    }
    private int sum = 0;
    private long oldtime=0;
    //展示测试的题目
    @SuppressLint("SetTextI18n")
    @Override
    public void ShowTest(List<HashMap> list) {
        lister = list;
        if (list.size() >= 0) {
            final long oldTime = System.currentTimeMillis();
            oldtime=oldTime;
            ic_load.setVisibility(View.GONE);
//            for (int i =0 ;i<=list.size();i++){
            HashMap map = list.get(i);
            if (list.size() == 0) {
                btn_submit.setVisibility(View.VISIBLE);
                lin_test.setVisibility(View.GONE);
                fragment_content.addView(activity_entitle);
                tx_testtitle.setText(map.get(ITestPresenter.TITLE) + "");
                firstRadio.setText(map.get(ITestPresenter.A) + "");
                secondRadio.setText(map.get(ITestPresenter.B) + "");
                thirdRadio.setText(map.get(ITestPresenter.C) + "");
                fourRadio.setText(map.get(ITestPresenter.D) + "");
                String state = map.get(ITestPresenter.TRUEOPTION) + "";
                final int TRUEOPTION = getRadioId(state);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (TRUEOPTION == checkedId) {
                           // score[0] = 1;
                            sum+=1;
                        } else {
                         //   score[0] = 0;
                            sum=0;
                        }
                    }
                });
                //点击提交按钮
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragment_content.addView(activity_entitle);
                        long newTime = System.currentTimeMillis();
                        Intent intent = new Intent(getActivity(), SecordActivity.class);
                        intent.putExtra("secord", sum);
                        intent.putExtra("coursename","4");
                        intent.putExtra("courseID","104");
                        intent.putExtra("time",newTime-oldtime+"");
                        startActivity(intent);
                    }
                });

            } else {
                if (i == 0) {
                 //btn_up.setVisibility(View.GONE);
                    btn_next.setVisibility(View.VISIBLE);
                    btn_submit.setVisibility(View.GONE);
                    fragment_content.addView(activity_entitle);
                    tx_testtitle.setText(map.get(ITestPresenter.TITLE) + "");
                    firstRadio.setText(map.get(ITestPresenter.A) + "");
                    secondRadio.setText(map.get(ITestPresenter.B) + "");
                    thirdRadio.setText(map.get(ITestPresenter.C) + "");
                    fourRadio.setText(map.get(ITestPresenter.D) + "");
                    String state = map.get(ITestPresenter.TRUEOPTION) + "";
                    final int TRUEOPTION = getRadioId(state);
                    final int finalI = i;
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                            if (TRUEOPTION == checkedId) {
                              //  score[finalI] = 1;
                                sum+=1;
                            } else {
                               //score[finalI] = 0;
                                sum=0;
                            }
                        }
                    });
                    btn_next.setOnClickListener(this);
                } else {
                    //// TODO: 2018/10/31 0031
                    lin_test.setVisibility(View.VISIBLE);
                    //点击提交按钮
                    btn_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            long newTime = System.currentTimeMillis();
                            fragment_content.removeView(activity_entitle);
                            Intent intent = new Intent(getActivity(), SecordActivity.class);
                            intent.putExtra("secord", sum);
                            intent.putExtra("coursename","4");
                            intent.putExtra("courseID","104");
                            intent.putExtra("time",newTime-oldtime+"");
                            startActivity(intent);
                        }
                    });
                }
            }
        }
    }
    public int getRadioId(String state){
        int id = 0;
        switch (state){
            case "A":
               id = R.id.firstRadio;
            break;
            case "B":
                id = R.id.secondRadio;
            break;
            case "C":
                id = R.id.thirdRadio;
            break;
            case "D":
                id = R.id.fourRadio;
            break;
        }
        return id;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        i++;
        fragment_content.removeView(activity_entitle);
        fragment_content.addView(activity_entitle);
        if (lister.size() == i) {
            HashMap map = lister.get(i-1);
            lin_test.setVisibility(View.GONE);
            btn_submit.setVisibility(View.VISIBLE);
            tx_testtitle.setText(map.get(ITestPresenter.TITLE) + "");
            firstRadio.setText(map.get(ITestPresenter.A) + "");
            secondRadio.setText(map.get(ITestPresenter.B) + "");
            thirdRadio.setText(map.get(ITestPresenter.C) + "");
            fourRadio.setText(map.get(ITestPresenter.D) + "");
            String state = map.get(ITestPresenter.TRUEOPTION) + "";
            final int TRUEOPTION = getRadioId(state);
//            final int finalI = i;
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (TRUEOPTION == checkedId) {
                     //   score[i] = 1;
                        sum+=1;
                    } else {
                     //   score[i] = 0;
                        sum-=1;
                    }
                }
            });
//            btn_next.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    fragment_content.removeView(activity_entitle);
//                }
//            });
          //  btn_next.setOnClickListener(this);
            //点击提交按钮
            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long newTime = System.currentTimeMillis();
                    Intent intent = new Intent(getActivity(), SecordActivity.class);
                    intent.putExtra("secord", sum);
                    intent.putExtra("coursename","4");
                    intent.putExtra("courseID","104");
                    intent.putExtra("time",newTime-oldtime+"");
                    startActivity(intent);
                }
            });
        } else {
            HashMap map = lister.get(i);
         //   btn_up.setVisibility(View.VISIBLE);
            btn_next.setVisibility(View.VISIBLE);
            btn_submit.setVisibility(View.GONE);
            tx_testtitle.setText(map.get(ITestPresenter.TITLE) + "");
            firstRadio.setText(map.get(ITestPresenter.A) + "");
            secondRadio.setText(map.get(ITestPresenter.B) + "");
            thirdRadio.setText(map.get(ITestPresenter.C) + "");
            fourRadio.setText(map.get(ITestPresenter.D) + "");
            String state = map.get(ITestPresenter.TRUEOPTION) + "";
            final int TRUEOPTION = getRadioId(state);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (TRUEOPTION == checkedId) {
                       // score[i] = 1;
                        sum+=1;
                    } else {
                      //  score[i] = 0;
                        sum-=1;
                    }

                }
            });
            btn_next.setOnClickListener(this);
        }
    }


}




