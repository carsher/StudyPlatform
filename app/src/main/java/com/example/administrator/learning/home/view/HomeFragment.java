package com.example.administrator.learning.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.learning.R;
import com.example.administrator.learning.common.database.DbAdapter;
import com.example.administrator.learning.common.http.OkHttpImp;
import com.example.administrator.learning.common.utils.Const;
import com.example.administrator.learning.common.utils.HorizontalListView;
import com.example.administrator.learning.common.utils.SpUtils;
import com.example.administrator.learning.common.utils.StaticCost;
import com.example.administrator.learning.home.common.homeEntey;
import com.example.administrator.learning.home.model.MainMangerImp;
import com.example.administrator.learning.home.presenter.HomeFragmentpresenterImp;
import com.example.administrator.learning.home.presenter.IHomeFragmentpresenterImp;
import com.example.administrator.learning.video.view.Qlayeractivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Handler;

/**
 * 	00218795
 * 	gentle8758
 * Created by Administrator on 2018/10/19 0019.
 */

public class HomeFragment extends Fragment implements IHomeFragment{
    private IHomeFragmentpresenterImp presenter;
    public View view;
    private HorizontalListView mylistview1;
    private GridView mylistview2;
    private TextView tv_school;
    private DbAdapter dbAdapter;
    private ViewPager viewPager;
    private List<ImageView> date1=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,null);
        if (dbAdapter == null){
            dbAdapter = new DbAdapter(getActivity());
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();


        if( presenter == null){
            presenter = new HomeFragmentpresenterImp(getActivity().getApplicationContext(),new MainMangerImp(OkHttpImp.getInstance()),this);
            presenter.register();
            presenter.getCourse(); //从服务器上获取数据
        }
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/19  15:56
     *  @方法说明： 初始化事件
     */
private void initUI() {
    tv_school = (TextView) view.findViewById(R.id.tv_school);

    mylistview1 = (HorizontalListView) view.findViewById(R.id.item_tv_channel);
//    mylistview2 = (HorizontalListView) view.findViewById(R.id.item_tv_channe2);
    mylistview2 = (GridView) view.findViewById(R.id.item_tv_channe2);

    viewPager=(ViewPager)view.findViewById(R.id.img_info) ;
  // int[] imgs = {R.drawable.l01, R.drawable.l02, R.drawable.l03};
    String[] imgs = {"http://www.gdit.edu.cn/_upload/article/e0/92/152f4ffe4286893409b7252067ed/c73293c6-2191-4620-baee-7034d25708bc.jpg",
           "http://www.gdit.edu.cn/_upload/article/ff/26/e95a351c41439b272396aa971a3b/62fe0e37-78e2-43b9-86cd-b89713170b00.jpg",
            "http://www.gdit.edu.cn/_upload/article/18/02/d620007f4e4a8f30e3065d65e892/0ca6704e-6202-4d83-8841-873cbfaa717f.jpg"};
    date1=new ArrayList<>();
    for(int i=0;i<imgs.length;i++){
        ImageView imageView=new ImageView(getActivity());
      //  imageView.setImageResource(imgs[i]);
        Picasso.with(getActivity().getApplicationContext()).load(imgs[i]).fit().into(imageView);

        date1.add(imageView);
    }
    viewPager.setAdapter(new mydape());
    isruning=true;
    handler.sendEmptyMessageDelayed(0,2000);

    mylistview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    });
}
    private  boolean isruning=false;
    android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            if(isruning){
                handler.sendEmptyMessageDelayed(0,2000);
            }
        }
    };


    class mydape extends PagerAdapter{
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position=position%date1.size();
            View view=date1.get(position);
            //使用viewparent来看看上一个是否有加入到一个viewgroup中，如果有就移开
            ViewParent vp=view.getParent();
            if(vp!=null){
                ViewGroup parent=(ViewGroup) vp;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //position=position%date1.size();
            //container.removeView(date1.get(position));
        }
        //一共有paper中一共要多少view
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        //判断是否是同一张图
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/24  19：35
     *  @方法说明： 显示课程信息
     */
    @Override
    public void ShowData(homeEntey entiy) {
        final List<HashMap> list = entiy.getList();
        final List<HashMap> listhistory = entiy.getListHistory();
        mylistview2.setAdapter(new myAdapter(list));
        mylistview1.setAdapter(new myAdapter(listhistory));
        //响应点击事件
        mylistview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap map = list.get(position);
                String imgname = map.get(IHomeFragmentpresenterImp.IMGPATH+"")+"";
                Log.e("HOMEFRAGMENT","HomeFragment"+list.size()+"------"+imgname);

                Toast.makeText(getActivity().getApplicationContext(),"选择了"+position,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(),Qlayeractivity.class);
                intent.putExtra("id",map.get(IHomeFragmentpresenterImp.ID)+"");
                intent.putExtra("imgpath",imgname);
                intent.putExtra("user", SpUtils.getString(getActivity(), StaticCost.USER)+"");
                startActivity(intent);
            }
        });
        mylistview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap map = listhistory.get(position);
                String imgname = map.get(IHomeFragmentpresenterImp.IMGPATH+"")+"";

                Toast.makeText(getActivity().getApplicationContext(),"选择了"+position,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(),Qlayeractivity.class);
                intent.putExtra("id",map.get(IHomeFragmentpresenterImp.ID)+"");
                intent.putExtra("imgpath",imgname);
                intent.putExtra("user", SpUtils.getString(getActivity(), StaticCost.USER)+"");
                startActivity(intent);
            }
        });
    }

    /**
     *  @创建用户 crash
     *  @创建时间 2018/10/24  19：35
     *  @方法说明： 没有课程时显示
     */
    @Override
    public void ShowNoData() {

    }

    @Override
    public void ShowToast(String say) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unregister();
        presenter.onDestroy();
        isruning=false;
        Log.e("HomeFragment：","由于系统资源匮乏，被回收了");
    }

    class myAdapter extends BaseAdapter{
        List<HashMap> lister = null;
        public myAdapter(List<HashMap> list){
            lister = list;
        }
        @Override
        public int getCount() {
            return lister.size();
        }

        @Override
        public Object getItem(int position) {
            return lister.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HashMap map = lister.get(position);
            Log.e("HOMEFRAGMENT","myAdapter"+map.get(IHomeFragmentpresenterImp.COURSENAME));
            View view = View.inflate(getActivity().getApplicationContext(),R.layout.item_historylist,null);
            ImageView img_course = (ImageView) view.findViewById(R.id.img_course);
            String imgname = map.get(IHomeFragmentpresenterImp.IMGPATH+"")+"";
            StringBuilder sb = new StringBuilder();
            sb.append(StaticCost.IP).append(imgname);
            String path = sb.toString();
            TextView tv_course = (TextView) view.findViewById(R.id.tv_course);
            Picasso.with(getActivity()).load(path).placeholder(R.drawable.loding_img).fit().into(img_course);
         //   img_course.setBackgroundResource((Integer) map.get("imgpath"));
            tv_course.setText(map.get(IHomeFragmentpresenterImp.COURSENAME)+"");
            return view;
        }
    }
}
