package com.example.administrator.learning.home.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.database.DbAdapter;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.common.view.CompletedView;
import com.example.administrator.learning.home.presenter.IHomeFragmentpresenterImp;
import com.example.administrator.learning.video.view.Qlayeractivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/10/19 0019.
 */

public class RecordFragment extends Fragment {
    private int mCurrentProgress = 0;
    private CompletedView mTasksView;//进度条
    private View view;
    private ListView lv_record;
    private DbAdapter dbAdapter;
    private List<HashMap> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record,null);
        if (dbAdapter == null){
             dbAdapter = new DbAdapter(getActivity());
            dbAdapter.open();
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void initUI() {
        String user = SpUtils.getString(getActivity(),StaticCost.USER);
        if (user.equals("null")){
            Log.e("user为空", "user为空：initUI: "+user);
            return;
        }else{
        Log.e("用户名为：", "用户名为：initUI: "+user);}

        lv_record = (ListView) view.findViewById(R.id.lv_record);
        list = dbAdapter.queryRecordAll(user);
  //      dbAdapter.queryBySql("DELETE FROM record_userhave_2");
        lv_record.setAdapter(new MyAdapter_record(list));
        lv_record.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap map = list.get(position);
                if (map.size()>=0){
                    Toast.makeText(getActivity().getApplicationContext(),"选择了"+map.get("title"),Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(getActivity(),Qlayeractivity.class);
                    intent.putExtra("id",map.get(IHomeFragmentpresenterImp.ID)+"");
                    intent.putExtra("user", SpUtils.getString(getActivity(), StaticCost.USER)+"");
                    intent.putExtra("imgpath",map.get("imgpath")+"");
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }

    private class MyAdapter_record extends BaseAdapter{
        private List<HashMap> list;
        public MyAdapter_record(List<HashMap> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size()-1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            position = position>2?2:position;
            View view = View.inflate(getActivity(),R.layout.item_record,null);
            HashMap map = list.get(position);
            ImageView img_course = (ImageView) view.findViewById(R.id.img_course_record);//图片
            TextView tv_courseName = (TextView) view.findViewById(R.id.course_name_record);//名称
            TextView tv_studytime = (TextView) view.findViewById(R.id.tv_studytime);//时间
            TextView tv_section_record = (TextView) view.findViewById(R.id.tv_section_record);
            mTasksView = (CompletedView) view.findViewById(R.id.tasks_view);//进度
            tv_courseName.setText(map.get("coursename")+"");
            tv_section_record.setText(map.get("title")+"");
            tv_studytime.setText(map.get("data")+"");
            String imgname = map.get("imgpath")+"";
            StringBuilder sb = new StringBuilder();
            sb.append(StaticCost.IP).append(imgname);
            String path = sb.toString();
            Picasso.with(getActivity()).load(path).placeholder(R.drawable.testdele).into(img_course);
            String mm1 = map.get("courrenttime")+"";
            String mm2 = map.get("coursetime")+"";
            String m1=mm1 == null||mm1.length() == 0?"0":mm1;
            String m2=mm2 == null||mm2.length() == 0?"0":mm2;
            return view;
        }
    }


//        mTasksView = (CompletedView) view.findViewById(R.id.tasks_view);
//  new Thread(new ProgressRunable()).start();
//    class ProgressRunable implements Runnable {
//        @Override
//        public void run() {
//            while (mCurrentProgress < mTotalProgress) {
//                mCurrentProgress += 1;
//                mTasksView.setProgress(mCurrentProgress);
//                try {
//                    Thread.sleep(90);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
