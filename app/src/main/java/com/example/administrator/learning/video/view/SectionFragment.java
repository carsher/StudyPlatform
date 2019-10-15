package com.example.administrator.learning. video.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.bean.sectionBean;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.video.common.IFragmentInteraction;
import com.example.administrator.learning.video.common.bean.onRebulidList;
import com.example.administrator.learning.video.common.bean.reList;
import com.example.administrator.learning.video.model.VideoMangerImp;
import com.example.administrator.learning.video.presenter.ISelectPresenterImp;
import com.example.administrator.learning.video.presenter.ISendVideoPresenterImp;
import com.example.administrator.learning.video.presenter.IplayerPresenter;
import com.example.administrator.learning.video.presenter.playerPresenterImp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/20 0020.
 * 章节
 */

public class SectionFragment extends Fragment implements IsectionFragment{
    private ISelectPresenterImp presenter;
    private ISendVideoPresenterImp presenterer;
    public View view;
    private ListView lV_section;
    // 2.1 定义用来与外部activity交互，获取到宿主activity
    private IFragmentInteraction listterner;
    private List<sectionBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_section,null);
        if(presenter == null){
            presenter = new ISelectPresenterImp(new VideoMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
        }
        if(presenterer == null){
            presenterer = new ISendVideoPresenterImp(new VideoMangerImp(OkHttpImp.getInstance()));
            presenterer.register();
        }
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof IFragmentInteraction) {
            listterner = (IFragmentInteraction)activity; // 2.2 获取到宿主activity并赋值
        } else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
        String titles = ((Qlayeractivity) activity).getTitles();//通过强转成宿主activity，就可以获取到传递过来的数据
        Log.e("titleweo", "onAttach: "+titles );
    }

    private void initUI() {
        lV_section = (ListView) view.findViewById(R.id.lv_section);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        listterner = null;
    }

    @Override
    public void showData(final List<sectionBean> list, int length, final List<HashMap> listList) {
        setdata(length,list);
        GroupListAdapter adapter = new GroupListAdapter(getActivity(), lists, listTag);

        lV_section.setAdapter(adapter);
        lV_section.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("点击position为", "onItemClick: "+position);
                position =  position==0?0:position-1;
                sectionBean bean = list.get(0);
                List<HashMap> list1 = bean.getList();
                HashMap map = listList.get(position);
             //   HashMap map = listList.get(position);
                Log.e("点击视频章节为：", "onItemClick: "+map.get("title")+"sss"+map.get("sid"));
                String learnNum = bean.getLearnNumber();
                String videoTime = map.get("viedo_length")+"";
                String videoURL = map.get("videopath")+"";
                //String sectionID = map.get("id")+"";
                String sectionID = map.get("id")+"";

                String testID = map.get("testid")+"";
                String title = "2.2线性布局";
                presenterer.sendVideoHistory(String.valueOf(map.get("id"))
                        ,SpUtils.getString(getActivity().getApplicationContext(),StaticCost.USER)
                        ,SpUtils.getString(getActivity().getApplicationContext(),StaticCost.NAME)
                ,SpUtils.getString(getActivity().getApplicationContext(),StaticCost.CLASSNAME));//标记某个用户查看了某一个小节的视频
                Log.e("SectionFragment","SectionFragment:上传成功！");
                if (learnNum == null||videoTime==null){
                    Log.e("我自然是空的", "onItemClick: ");
                    listterner.process("1111","1111","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","1","1","1"); // 3.1 执行回调
                    return;
                }
                listterner.process(learnNum,videoTime,videoURL,sectionID,testID,title); // 3.1 执行回调
            }
        });
    }

    @Override
    public void showDataer(List<sectionBean> list) {
        Log.e("FFFFFFFFFFFFFFFFFFF","FFFFF: "+list.get(1).getKnobble());

    }


    private List<String> lists = new ArrayList<String>();
    private List<String> listTag = new ArrayList<String>();

    public void setdata(int jsonLength,List<sectionBean> list){
        try{
            for (int i=0;i<jsonLength;i++){
                HashMap map = new HashMap();
                sectionBean bea = list.get(i);
                lists.add(bea.getKnobble());
                listTag.add(bea.getKnobble());
                for (int j=0;j<Integer.parseInt(bea.getSmallsection());j++){
                    List<HashMap> listmap = bea.getList();
                    HashMap maper = listmap.get(j);
                    Log.e("titltltltltl", "setdata: "+maper.get(reList.title) +"ddd");
                    lists.add(maper.get(reList.title)+"");
                }

            }
        }catch(Exception e){
            Log.e("errrr",e.getMessage()+"aaaaaaaaa");
        }
    }


    private static class GroupListAdapter extends ArrayAdapter<String> {
        private List<String> listTag = null;
        public GroupListAdapter(Context context, List<String> objects, List<String> tags) {
            super(context, 0, objects);
            this.listTag = tags;
        }
        @Override
        public boolean isEnabled(int position) {
            if(listTag.contains(getItem(position))){
                return false;
            }
            return super.isEnabled(position);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e(":","-----------------------------------------");
            View view = convertView;
            TextView textView;
            if(listTag.contains(getItem(position))){
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_section, null);
                textView = (TextView) view.findViewById(R.id.tv_knobble);
            }else{
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_section2, null);
                textView = (TextView) view.findViewById(R.id.tv_knobble);
            }
            Log.e("GroupListAdapter", "getView: " + getItem(position));
            textView.setText(getItem(position));
            return view;
        }
    }

    @Override
    public void ShowToast(String say) {
        Toast.makeText(getActivity(),say,Toast.LENGTH_SHORT).show();
    }
}
